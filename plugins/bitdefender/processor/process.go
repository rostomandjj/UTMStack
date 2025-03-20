package processor

import (
	"context"
	"fmt"
	"os"

	"github.com/threatwinds/go-sdk/catcher"
	"github.com/threatwinds/go-sdk/plugins"
	"github.com/threatwinds/go-sdk/utils"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

var LogsChan = make(chan *plugins.Log)

func ProcessLogs() {
	filePath, err := utils.MkdirJoin(plugins.WorkDir, "sockets")
	if err != nil {
		_ = catcher.Error("cannot create socket directory", err, nil)
		os.Exit(1)
	}

	socketPath := filePath.FileJoin("engine_server.sock")

	conn, err := grpc.NewClient(
		fmt.Sprintf("unix://%s", socketPath),
		grpc.WithTransportCredentials(insecure.NewCredentials()),
	)
	if err != nil {
		_ = catcher.Error("failed to connect to engine server", err, map[string]any{})
		os.Exit(1)
	}

	client := plugins.NewEngineClient(conn)

	inputClient, err := client.Input(context.Background())
	if err != nil {
		_ = catcher.Error("failed to create input client", err, map[string]any{})
		os.Exit(1)
	}

	go func() {
		log := <-LogsChan
		err := inputClient.Send(log)
		if err != nil {
			_ = catcher.Error("failed to send log", err, map[string]any{})
			os.Exit(1)
		}
	}()

	for {
		_, err = inputClient.Recv()
		if err != nil {
			_ = catcher.Error("failed to receive ack", err, map[string]any{})
			os.Exit(1)
		}
	}
}
