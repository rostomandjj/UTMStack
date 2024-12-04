package updater

import (
	"fmt"
	"net/http"
	"strings"
	"sync"
	"time"

	"github.com/utmstack/UTMStack/installer/config"
	"github.com/utmstack/UTMStack/installer/utils"
)

type UpdaterClient struct {
	Server      string `yaml:"server"`
	InstanceID  string `yaml:"instance_id"`
	InstanceKey string `yaml:"instance_key"`
}

var (
	updaterClient     *UpdaterClient
	updaterClientOnce sync.Once
)

func GetUpdaterClient() *UpdaterClient {
	updaterClientOnce.Do(func() {
		cnf := config.GetConfig()
		updaterClient = &UpdaterClient{}

		err := utils.ReadYAML(config.UpdaterConfigPath, updaterClient)
		if err != nil || updaterClient.Server == "" || updaterClient.InstanceID == "" || updaterClient.InstanceKey == "" {
			switch cnf.Branch {
			case "dev":
				updaterClient.Server = config.CMDev
			case "qa":
				updaterClient.Server = config.CMQa
			case "rc":
				updaterClient.Server = config.CMRc
			case "prod":
				updaterClient.Server = config.CMProd
			}

			err := updaterClient.RegisterClient()
			if err != nil {
				utils.Logger.Fatal("error registering instance: %v", err)
			}
		}
	})

	return updaterClient
}

func (c *UpdaterClient) RegisterClient() error {
	tlsConfig, err := utils.LoadTLSCredentials(config.CertFilePath)
	if err != nil {
		return fmt.Errorf("error loading tls credentials: %v", err)
	}

	resp, status, err := utils.DoReq[Auth](c.Server+config.RegisterInstanceEndpoint, nil, http.MethodGet, nil, tlsConfig)
	if err != nil {
		return fmt.Errorf("error doing request: %v", err)
	} else if status != http.StatusOK {
		return fmt.Errorf("status code %d: %v", status, resp)
	}

	c.InstanceID = resp.ID
	c.InstanceKey = resp.Key

	err = utils.WriteYAML(config.UpdaterConfigPath, c)
	if err != nil {
		return fmt.Errorf("error writing config file: %v", err)
	}

	return nil
}

func (c *UpdaterClient) UpdateProcess() {
	for {
		time.Sleep(config.CheckUpdatesEvery)
		err := c.UpdateEdition()
		if err != nil {
			utils.Logger.ErrorF("error updating edition: %v", err)
		}

		if config.IsInMaintenanceWindow() {
			err := c.CheckUpdate(true, true)
			if err != nil {
				utils.Logger.ErrorF("error checking update: %v", err)
			}
		}
	}
}

func (c *UpdaterClient) CheckUpdate(download bool, runCmds bool) error {
	tlsConfig, err := utils.LoadTLSCredentials(config.CertFilePath)
	if err != nil {
		return fmt.Errorf("error loading tls credentials: %v", err)
	}

	resp, status, err := utils.DoReq[[]MasterVersion](
		c.Server+config.GetUpdatesInfoEndpoint,
		nil,
		http.MethodGet,
		map[string]string{"instance-id": c.InstanceID, "instance-key": c.InstanceKey},
		tlsConfig,
	)
	if err != nil {
		return fmt.Errorf("error doing request: %v", err)
	} else if status != http.StatusOK {
		return fmt.Errorf("status code %d: %v", status, resp)
	}

	for _, master := range resp {
		utils.Logger.Info("Updating UTMStack to version %s", master.VersionName)
		versions := GetVersionsFromMaster(master)
		err := SaveVersions(versions)
		if err != nil {
			return fmt.Errorf("error saving versions: %v", err)
		}

		for _, cv := range master.ComponentVersions {
			if download {
				utils.Logger.Info("Downloading files for component %s version %s", cv.Component.Name, cv.VersionName)
				for _, f := range cv.Files {
					err = DownloadFile(
						f,
						fmt.Sprintf("%s%s?file-id=%s", c.Server, config.GetFileEndpoint, f.ID),
						map[string]string{"instance-id": c.InstanceID, "instance-key": c.InstanceKey},
						tlsConfig,
					)
					if err != nil {
						return fmt.Errorf("error downloading file: %v", err)
					}
				}
			}

			if runCmds {
				utils.Logger.Info("Running post commands for component %s version %s", cv.Component.Name, cv.VersionName)
				for _, cmd := range cv.Scripts {
					parts := strings.Split(cmd.Script, " ")
					cmd := parts[0]
					args := parts[1:]
					err = utils.RunCmd(cmd, args...)
					if err != nil {
						return fmt.Errorf("error running command: %v", err)
					}
				}
			}
		}
	}

	return nil
}

func (c *UpdaterClient) UpdateEdition() error {
	tlsConfig, err := utils.LoadTLSCredentials(config.CertFilePath)
	if err != nil {
		return fmt.Errorf("error loading tls credentials: %v", err)
	}

	resp, status, err := utils.DoReq[string](
		GetUpdaterClient().Server+config.GetEditionEndpoint,
		nil,
		http.MethodGet, map[string]string{"instance-id": GetUpdaterClient().InstanceID, "instance-key": GetUpdaterClient().InstanceKey},
		tlsConfig,
	)
	if err != nil {
		return fmt.Errorf("error getting edition: %v", err)
	} else if status != http.StatusOK {
		return fmt.Errorf("error getting edition: %v", resp)
	}

	edition := config.Edition{
		Edition: resp,
	}

	return config.SaveEdition(&edition)
}
