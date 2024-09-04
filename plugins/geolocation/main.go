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

func (p *parsingServer) ParseLog(ctx context.Context, transform *go_sdk.Transform) (*go_sdk.JLog, error) {
	source, ok := transform.Step.Dynamic.Params["source"]
	if !ok {
		return transform.Jlog, fmt.Errorf("'source' parameter required")
	}

	destination, ok := transform.Step.Dynamic.Params["destination"]
	if !ok {
		return transform.Jlog, fmt.Errorf("'destination' parameter required")
	}

	value := gjson.Get(transform.Jlog.Log, source.String()).String()
	if value == "" {
		return transform.Jlog, fmt.Errorf("source field not found")
	}

	geo := geolocate(value)

	var err error
	transform.Jlog.Log, err = sjson.Set(transform.Jlog.Log, destination.String(), geo)
	if err != nil {
		return transform.Jlog, err
	}

	return transform.Jlog, nil
}
