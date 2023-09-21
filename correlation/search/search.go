package search

import (
	"fmt"
	"strings"

	"github.com/utmstack/UTMStack/correlation/utils"
	"github.com/tidwall/gjson"
)

func Search(query string) []string {
	var result []string
	cnf := utils.GetConfig()
	url := fmt.Sprintf("%s/log-*/_search", cnf.Elasticsearch)
	cnn, err := utils.DoPost(url, "application/json", strings.NewReader(query))
	if err != nil {
		h.Error("Could not request logs to Elasticsearch: %v", err)
	} else {
		hits := gjson.Get(string(cnn), "hits.hits").Array()
		for _, hit := range hits {
			log := gjson.Get(hit.String(), "_source")
			result = append(result, log.String())
		}
	}
	return result
}
