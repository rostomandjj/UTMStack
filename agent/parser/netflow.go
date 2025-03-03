package parser

import (
	"fmt"
	"sync"

	"github.com/tehmaze/netflow"
	"github.com/tehmaze/netflow/ipfix"
	"github.com/tehmaze/netflow/netflow1"
	"github.com/tehmaze/netflow/netflow5"
	"github.com/tehmaze/netflow/netflow6"
	"github.com/tehmaze/netflow/netflow7"
	"github.com/tehmaze/netflow/netflow9"
	"github.com/threatwinds/go-sdk/plugins"
	"github.com/threatwinds/validations"
	"github.com/utmstack/UTMStack/agent/config"
	pnf "github.com/utmstack/UTMStack/agent/parser/netflow"
	"github.com/utmstack/UTMStack/agent/utils"
)

var (
	netflowParser = NetflowParser{}
	netflowOnce   sync.Once
)

type NetflowParser struct {
}

func GetNetflowParser() *NetflowParser {
	netflowOnce.Do(func() {
		netflowParser = NetflowParser{}
	})
	return &netflowParser
}

type NetflowObject struct {
	Remote  string
	Message netflow.Message
}

func (p *NetflowParser) ProcessData(logMessage interface{}, _ string, queue chan *plugins.Log) error {
	var metrics []pnf.Metric
	var remote string

	switch l := logMessage.(type) {
	case NetflowObject:
		remote = l.Remote
		switch m := l.Message.(type) {
		case *netflow1.Packet:
			metrics = pnf.PrepareV1(remote, m)
		case *netflow5.Packet:
			metrics = pnf.PrepareV5(remote, m)
		case *netflow6.Packet:
			metrics = pnf.PrepareV6(remote, m)
		case *netflow7.Packet:
			metrics = pnf.PrepareV7(remote, m)
		case *netflow9.Packet:
			metrics = pnf.PrepareV9(remote, m)
		case *ipfix.Message:
			metrics = pnf.PrepareIPFIX(remote, m)
		}
	default:
		return fmt.Errorf("unknown log batch type: %T", l)
	}

	messages := pnf.Dump(metrics)

	for _, msg := range messages {
		message, _, err := validations.ValidateString(msg, false)
		if err != nil {
			utils.Logger.ErrorF("error validating string: %v: message: %s", err, message)
			continue
		}
		queue <- &plugins.Log{
			DataType:   string(config.DataTypeNetflow),
			DataSource: remote,
			Raw:        msg,
		}
	}

	return nil
}
