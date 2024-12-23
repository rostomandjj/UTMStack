package main

import (
	"context"
	"fmt"
	"net"
	"os"
	"path"

	go_sdk "github.com/threatwinds/go-sdk"
	"github.com/tidwall/gjson"
	"github.com/tidwall/sjson"
	"google.golang.org/grpc"
)

type parsingServer struct {
	go_sdk.UnimplementedParsingServer
}

func main() {
	os.Remove(path.Join(go_sdk.GetCfg().Env.Workdir, "sockets", "com.utmstack.geolocation_parsing.sock"))

	laddr, err := net.ResolveUnixAddr("unix", path.Join(go_sdk.GetCfg().Env.Workdir, "sockets", "com.utmstack.geolocation_parsing.sock"))
	if err != nil {
		go_sdk.Logger().ErrorF(err.Error())
		os.Exit(1)
	}

	listener, err := net.ListenUnix("unix", laddr)
	if err != nil {
		go_sdk.Logger().ErrorF(err.Error())
		os.Exit(1)
	}

	grpcServer := grpc.NewServer()
	go_sdk.RegisterParsingServer(grpcServer, &parsingServer{})

	go update()

	if err := grpcServer.Serve(listener); err != nil {
		go_sdk.Logger().ErrorF(err.Error())
		os.Exit(1)
	}
}

func (p *parsingServer) ParseLog(ctx context.Context, transform *go_sdk.Transform) (*go_sdk.Draft, error) {
	m := go_sdk.NewMetter("ParseLog")
	defer m.Elapsed("finished")
	
	source, ok := transform.Step.Dynamic.Params["source"]
	if !ok {
		go_sdk.Logger().ErrorF("'source' parameter required")
		return transform.Draft, fmt.Errorf("'source' parameter required")
	}

	destination, ok := transform.Step.Dynamic.Params["destination"]
	if !ok {
		go_sdk.Logger().ErrorF("'destination' parameter required")
		return transform.Draft, fmt.Errorf("'destination' parameter required")
	}

	value := gjson.Get(transform.Draft.Log, source.GetStringValue()).String()
	if value == "" {
		go_sdk.Logger().LogF(100, "source field not found: %s", source.GetStringValue())
		return transform.Draft, nil
	}

	geo := geolocate(value)

	if geo == nil {
		go_sdk.Logger().LogF(100, "geolocation not found for: %s", value)
		return transform.Draft, nil
	}

	var err error
	transform.Draft.Log, err = sjson.Set(transform.Draft.Log, destination.GetStringValue(), geo)
	if err != nil {
		go_sdk.Logger().ErrorF(err.Error())
		return transform.Draft, err
	}

	return transform.Draft, nil
}
