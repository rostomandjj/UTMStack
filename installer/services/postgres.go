package services

import (
	"database/sql"
	"fmt"

	_ "github.com/lib/pq"
	"github.com/utmstack/UTMStack/installer/config"
)

func InitPgUtmstack(c *config.Config) error {
	// Connecting to PostgreSQL
	psqlconn := fmt.Sprintf("host=localhost port=5432 user=postgres password=%s sslmode=disable",
		c.Password)
	srv, err := sql.Open("postgres", psqlconn)
	if err != nil {
		return err
	}

	// Close connection when finish
	defer srv.Close()

	// Check connection status
	err = srv.Ping()
	if err != nil {
		return err
	}

	// Creating utmstack
	_, err = srv.Exec("CREATE DATABASE utmstack")
	if err != nil && err.Error() != "pq: database \"utmstack\" already exists" {
		return err
	}

	// Creating agentmanager
	_, err = srv.Exec("CREATE DATABASE agentmanager")
	if err != nil && err.Error() != "pq: database \"agentmanager\" already exists" {
		return err
	}

	// Connecting to utmstack
	psqlconn = fmt.Sprintf("host=localhost port=5432 user=postgres password=%s sslmode=disable database=utmstack",
		c.Password)
	db, err := sql.Open("postgres", psqlconn)
	if err != nil {
		return err
	}

	// Close connection when finish
	defer db.Close()

	// Check connection status
	err = db.Ping()
	if err != nil {
		return err
	}

	// Creating utm_client
	_, err = db.Exec(`CREATE TABLE public.utm_client (		
id serial NOT NULL,
client_name varchar(100) NULL,
client_domain varchar(100) NULL,
client_prefix varchar(10) NULL,
client_mail varchar(100) NULL,
client_user varchar(50) NULL,
client_pass varchar(50) NULL,
client_licence_creation timestamp(0) NULL,
client_licence_expire timestamp(0) NULL,
client_licence_id varchar(100) NULL,
client_licence_verified bool NOT NULL,
CONSTRAINT utm_client_pkey PRIMARY KEY (id)
);`)
	if err != nil && err.Error() != "pq: relation \"utm_client\" already exists" {
		return err
	}

	// Insert client data
	_, err = db.Exec(`INSERT INTO public.utm_client (client_licence_verified) VALUES (false);`)
	if err != nil && err.Error() != "pq: duplicate key value violates unique constraint \"utm_client_pkey\"" {
		return err
	}

	return nil
}

func InitPgUserAuditor(c *config.Config) error {
	// Connecting to PostgreSQL
	psqlconn := fmt.Sprintf("host=localhost port=5432 user=postgres password=%s sslmode=disable",
		c.Password)
	srv, err := sql.Open("postgres", psqlconn)
	if err != nil {
		return err
	}

	// Close connection when finish
	defer srv.Close()

	// Check connection status
	err = srv.Ping()
	if err != nil {
		return err
	}

	// Creating user-auditor
	_, err = srv.Exec("CREATE DATABASE userauditor")
	if err != nil && err.Error() != "pq: database \"userauditor\" already exists" {
		return err
	}

	return nil
}
