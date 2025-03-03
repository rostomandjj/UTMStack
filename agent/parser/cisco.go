package parser

import (
	"fmt"
	"regexp"
	"sync"

	"github.com/threatwinds/go-sdk/plugins"
	"github.com/utmstack/UTMStack/agent/config"
)

var (
	ciscoParser      = CiscoParser{}
	ciscoParserOnce  sync.Once
	expressionsCisco = map[config.DataType]string{
		config.DataTypeCiscoAsa:       `%ASA-`,
		config.DataTypeCiscoFirepower: `%FTD-`,
		config.DataTypeCiscoSwitch:    `%(\w|_)+-((\b\w+\b-\b\w+\b-)?)(\d)-([A-Z]|_)+`,
	}
)

type CiscoParser struct{}

func GetCiscoParser() *CiscoParser {
	ciscoParserOnce.Do(func() {
		ciscoParser = CiscoParser{}
	})
	return &ciscoParser
}

func (p *CiscoParser) IdentifySource(log string) (config.DataType, error) {
	for logType, expression := range expressionsCisco {
		regExpCompiled, err := regexp.Compile(expression)
		if err != nil {
			return "", err
		}
		if regExpCompiled.MatchString(log) {
			return logType, nil
		}
	}
	return config.DataTypeCiscoMeraki, nil
}

func (p *CiscoParser) ProcessData(logMessage interface{}, datasource string, queue chan *plugins.Log) error {
	log, ok := logMessage.(string)
	if !ok {
		return fmt.Errorf("log is not of type string")
	}
	logType, err := p.IdentifySource(log)
	if err != nil {
		return err
	}
	queue <- &plugins.Log{
		DataType:   string(logType),
		DataSource: datasource,
		Raw:        log,
	}
	return nil
}
