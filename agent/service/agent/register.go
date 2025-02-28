package agent

import (
	"context"
	"fmt"
	"strings"

	"github.com/utmstack/UTMStack/agent/service/config"
	"github.com/utmstack/UTMStack/agent/service/conn"
	"github.com/utmstack/UTMStack/agent/service/models"
	"github.com/utmstack/UTMStack/agent/service/utils"
	"google.golang.org/grpc/metadata"
)

func RegisterAgent(cnf *config.Config, UTMKey string) error {
	connection, err := conn.GetAgentManagerConnection(cnf)
	if err != nil {
		return fmt.Errorf("error connecting to Agent Manager: %v", err)
	}

	agentClient := NewAgentServiceClient(connection)
	ctx, cancel := context.WithCancel(context.Background())
	ctx = metadata.AppendToOutgoingContext(ctx, "connection-key", UTMKey)
	defer cancel()

	ip, err := utils.GetIPAddress()
	if err != nil {
		return err
	}

	osInfo, err := utils.GetOsInfo()
	if err != nil {
		return fmt.Errorf("error getting os info: %v", err)
	}

	version := models.VersionResponse{}
	err = utils.ReadJson(config.VersionPath, &version)
	if err != nil {
		utils.Logger.Fatal("error reading version file: %v", err)
	}

	request := &AgentRequest{
		Ip:             ip,
		Hostname:       osInfo.Hostname,
		Os:             osInfo.OsType,
		Platform:       osInfo.Platform,
		Version:        version.Version,
		RegisterBy:     osInfo.CurrentUser,
		Mac:            osInfo.Mac,
		OsMajorVersion: osInfo.OsMajorVersion,
		OsMinorVersion: osInfo.OsMinorVersion,
		Aliases:        osInfo.Aliases,
		Addresses:      osInfo.Addresses,
	}

	response, err := agentClient.RegisterAgent(ctx, request)
	if err != nil {
		if strings.Contains(err.Error(), "hostname has already been registered") {
			return fmt.Errorf("failed to register agent: hostname has already been registered")
		}
		return fmt.Errorf("failed to register agent: %v", err)
	}

	cnf.AgentID = uint(response.Id)
	cnf.AgentKey = response.Key

	utils.Logger.Info("successfully registered agent")

	return nil
}
