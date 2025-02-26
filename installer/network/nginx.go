package network

import (
	"path"

	"github.com/utmstack/UTMStack/installer/config"
	"github.com/utmstack/UTMStack/installer/docker"
	"github.com/utmstack/UTMStack/installer/templates"
	"github.com/utmstack/UTMStack/installer/utils"
)

type NginxConfig struct {
	SharedKey string
}

func InstallNginx() error {
	env := []string{"DEBIAN_FRONTEND=noninteractive"}

	if err := utils.RunEnvCmd(env, "apt-get", "update"); err != nil {
		return err
	}

	if err := utils.RunEnvCmd(env, "apt-get", "install", "-y", "nginx"); err != nil {
		return err
	}

	return nil
}

func ConfigureNginx(conf *config.Config, stack *docker.StackConfig) error {
	c := NginxConfig{
		SharedKey: conf.InternalKey,
	}

	err := utils.GenerateConfig(c, templates.FrontEnd, path.Join(stack.FrontEndNginx, "00_nginx_panel.conf"))
	if err != nil {
		return err
	}

	err = utils.GenerateConfig(c, templates.Proxy, path.Join("/", "etc", "nginx", "sites-available", "default"))
	if err != nil {
		return err
	}

	if err := utils.RunCmd("systemctl", "restart", "nginx"); err != nil {
		return err
	}

	return nil
}
