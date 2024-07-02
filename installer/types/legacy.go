package types

import (
	"os"
	"path"

	"gopkg.in/yaml.v3"
)

type PluginsConfig struct {
	Plugins map[string]PluginConfig `yaml:"plugins"`
}

type PluginConfig struct {
	RulesFolder   string `yaml:"rulesFolder"`
	GeoIPFolder   string `yaml:"geoipFolder"`
	Elasticsearch string `yaml:"elasticsearch"`
	PostgreSQL    PostgreConfig `yaml:"postgresql"`
}

type PostgreConfig struct {
	Server   string `yaml:"server"`
	Port     string `yaml:"port"`
	User     string `yaml:"user"`
	Password string `yaml:"password"`
	Database string `yaml:"database"`
}

func (c *PluginsConfig) Set(conf *Config, stack *StackConfig) error {
	c.Plugins = make(map[string]PluginConfig)

	c.Plugins["com.utmstack.legacy"]= PluginConfig{
		RulesFolder : "/workdir/rules",
		GeoIPFolder : "/workdir/geolocation",
		Elasticsearch : "http://node1:9200",
		PostgreSQL: PostgreConfig{
			Server: "postgres",
			Port: "5432",
			User: "postgres",
			Password: conf.Password,
			Database: "utmstack",
		},
	}
	
	config, err := yaml.Marshal(c)
	if err != nil {
		return err
	}

	err = os.WriteFile(path.Join(stack.EventsEngineWorkdir, "pipeline", "plugins_legacy.yaml"), config, 0644)
	if err != nil {
		return err
	}

	return nil
}
