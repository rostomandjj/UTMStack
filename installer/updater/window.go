package updater

import (
	"fmt"
	"strings"
	"time"

	"github.com/utmstack/UTMStack/installer/config"
	"github.com/utmstack/UTMStack/installer/utils"
)

// MaintenanceWindow struct
// Frequency: daily, weekend, workday, or comma separated days
// StartTime: start time in 24 hour format(HH:MM)
// EndTime: end time in 24 hour format(HH:MM)
type MaintenanceWindow struct {
	Frequency string `yaml:"frequency"`
	StartTime string `yaml:"start_time"`
	EndTime   string `yaml:"end_time"`
}

func GetWindowConfig() (*MaintenanceWindow, bool) {
	if !utils.CheckIfPathExist(config.WindowConfigPath) {
		windowConfig := &MaintenanceWindow{
			Frequency: "daily",
			StartTime: "00:00",
			EndTime:   "02:00",
		}
		utils.WriteYAML(config.WindowConfigPath, windowConfig)
		return windowConfig, false
	}

	windowConfig := &MaintenanceWindow{}
	err := utils.ReadYAML(config.WindowConfigPath, windowConfig)
	if err != nil {
		fmt.Printf("error reading window config file: %v", err)
	}

	return windowConfig, true
}

func IsInMaintenanceWindow() bool {
	config, _ := GetWindowConfig()

	now := time.Now()
	weekday := now.Weekday().String()
	startTime, _ := time.Parse("15:04", config.StartTime)
	endTime, _ := time.Parse("15:04", config.EndTime)

	start := time.Date(now.Year(), now.Month(), now.Day(), startTime.Hour(), startTime.Minute(), 0, 0, now.Location())
	end := time.Date(now.Year(), now.Month(), now.Day(), endTime.Hour(), endTime.Minute(), 0, 0, now.Location())

	if end.Before(start) {
		end = end.Add(24 * time.Hour)
	}

	inTimeWindow := now.After(start) && now.Before(end)

	if config.Frequency == "daily" {
		return inTimeWindow
	}

	if config.Frequency == "weekend" {
		return (weekday == "Saturday" || weekday == "Sunday") && inTimeWindow
	}

	if config.Frequency == "workday" {
		return (weekday != "Saturday" && weekday != "Sunday") && inTimeWindow
	}

	days := strings.Split(config.Frequency, ",")
	for _, day := range days {
		if strings.TrimSpace(strings.ToLower(day)) == strings.ToLower(weekday) {
			return inTimeWindow
		}
	}

	return false
}
