package main

import (
	"context"
	"github.com/threatwinds/go-sdk/catcher"
	"github.com/threatwinds/go-sdk/plugins"
	"github.com/threatwinds/go-sdk/utils"
	"net"
	"os"
	"path"

	"github.com/tidwall/gjson"
	"github.com/tidwall/sjson"
	"google.golang.org/grpc"
)

type parsingServer struct {
	plugins.UnimplementedParsingServer
}

func main() {
	_ = os.Remove(path.Join(plugins.GetCfg().Env.Workdir, "sockets", "com.utmstack.geolocation_parsing.sock"))

	unixAddress, err := net.ResolveUnixAddr("unix", path.Join(plugins.GetCfg().Env.Workdir, "sockets", "com.utmstack.geolocation_parsing.sock"))
	if err != nil {
		_ = catcher.Error("cannot resolve unix address", err, nil)
		os.Exit(1)
	}

	listener, err := net.ListenUnix("unix", unixAddress)
	if err != nil {
		_ = catcher.Error("cannot listen to unix socket", err, nil)
		os.Exit(1)
	}

	grpcServer := grpc.NewServer()
	plugins.RegisterParsingServer(grpcServer, &parsingServer{})

	go update()

	if err := grpcServer.Serve(listener); err != nil {
		_ = catcher.Error("cannot serve grpc", err, nil)
		os.Exit(1)
	}
}

func (p *parsingServer) ParseLog(_ context.Context, transform *plugins.Transform) (*plugins.Draft, error) {
	m := utils.NewMeter("ParseLog")
	defer m.Elapsed("finished")

	source, ok := transform.Step.Dynamic.Params["source"]
	if !ok {
		return transform.Draft, catcher.Error("'source' parameter required", nil, nil)
	}

	destination, ok := transform.Step.Dynamic.Params["destination"]
	if !ok {
		return transform.Draft, catcher.Error("'destination' parameter required", nil, nil)
	}

	value := gjson.Get(transform.Draft.Log, source.GetStringValue()).String()
	if value == "" {
		return transform.Draft, nil
	}

	geo := geolocate(value)

	if geo == nil {
		return transform.Draft, nil
	}

	var err error
	transform.Draft.Log, err = sjson.Set(transform.Draft.Log, destination.GetStringValue(), geo)
	if err != nil {
		return transform.Draft, catcher.Error("failed to set geolocation", err, nil)
	}

	return transform.Draft, nil
}
