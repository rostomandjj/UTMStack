package main

import (
	"flag"
	"fmt"
	"log"
	"math"
	"os"
	"path/filepath"

	"github.com/dchest/uniuri"
	"github.com/pbnjay/memory"
)

const (
	master = 0
	probe  = 1
)

func main() {
	remove := flag.Bool("remove", false, "Remove application's docker containers")
	user := flag.String("db-user", "", "User name that will be used for database connections")
	pass := flag.String("db-pass", "", "Password for the database user. Please use a secure password")
	fqdn := flag.String("fqdn", "", "Full qualified domain name, example: utmmaster.utmstack.com")
	customerName := flag.String("customer-name", "", "Your name, example: John Doe")
	customerEmail := flag.String("customer-email", "", "A valid email address to send important notifications about the system health. Example: john@doe.com")
	datadir := flag.String("datadir", "", "Data directory")
	flag.Parse()

	if *remove {
		uninstall()
	} else {
		if *user == "" || *pass == "" || *datadir == "" || *fqdn == "" || *customerName == "" || *customerEmail == "" {
			log.Fatal("ERROR: Missing arguments")
		}
		install(*user, *pass, *datadir, *fqdn, *customerName, *customerEmail)
	}
}

func uninstall() {
	check(runCmd("docker", "stack", "rm", "utmstack"))
}

func install(user, pass, datadir, fqdn, customerName, customerEmail string) {
	serverName, err := os.Hostname()
	check(err)
	secret := uniuri.NewLen(10)
	esData := filepath.Join(datadir, "elasticsearch", "data")
	esBackups := filepath.Join(datadir, "elasticsearch", "backups")
	nginxCert := filepath.Join(datadir, "nginx", "cert")

	// create data folders
	os.MkdirAll(esData, os.ModePerm)
	os.MkdirAll(esBackups, os.ModePerm)
	os.MkdirAll(nginxCert, os.ModePerm)

	// set data folders permissions
	runCmd("chmod", "777", esData)
	runCmd("chmod", "777", esBackups)
	runCmd("chmod", "777", nginxCert)

	// set map_max_count size to 262144
	runCmd("sysctl", "-w", "vm.max_map_count=262144")
	//TODO Fix this command to do this config permanent
	runCmd("echo", "vm.max_map_count=262144", ">>", "/etc/sysctl.conf")

	// setup docker
	if runCmd("docker", "version") != nil {
		installDocker()
	}
	runCmd("docker", "swarm", "init")

	const(
		repo = "utmstack.azurecr.io/"
	)

	containersImages := [10]string{
		repo + "opendistro:1.11.0",
		repo + "openvas:11",
		repo + "logstash:7.9.3",
		repo + "opendistro-kibana:1.11.0",
		repo + "rsyslog:8.36.0",
		repo + "wazuh:3.11.1",
		repo + "scanner:1.0.0",
		repo + "nginx:1.19.5",
		repo + "panel:7.0.0",
		repo + "datasources:7.0.0",
	}

	for _, s := range containersImages {
		check(runCmd("docker", "pull", s))
	}

	// generate composer file and deploy
	f, err := os.Create(composerFile)
	check(err)
	defer f.Close()
	f.WriteString(composerTemplate)
	env := []string{
		"SERVER_TYPE=aio",
		"SERVER_NAME=" + serverName,
		"DB_USER=" + user,
		"DB_PASS=" + pass,
		"CLIENT_DOMAIN=" + fqdn,
		"CLIENT_NAME=" + customerName,
		"CLIENT_MAIL=" + customerEmail,
		"CLIENT_SECRET=" + secret,
		fmt.Sprint("ES_MEM=", (memory.TotalMemory()/uint64(math.Pow(1024, 3)) - 4) / 2),
		"ES_DATA=" + esData,
		"ES_BACKUPS=" + esBackups,
		"NGINX_CERT=" + nginxCert,
	}
	check(runEnvCmd(env, "docker", "stack", "deploy", "--compose-file", composerFile, stackName))

	// configure elastic
	initializeElastic(secret)
}
