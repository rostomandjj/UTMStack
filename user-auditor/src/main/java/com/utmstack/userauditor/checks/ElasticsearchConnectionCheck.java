package com.utmstack.userauditor.checks;

import com.utmstack.userauditor.service.elasticsearch.Constants;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.util.Assert;

import java.util.Objects;

public class ElasticsearchConnectionCheck {
    private static final String CLASSNAME = "ElasticsearchConnectionCheck";

    public static ElasticsearchConnectionCheck getInstance() {
        return new ElasticsearchConnectionCheck();
    }

    public void connectionCheck(int retries) {
        final String ctx = CLASSNAME + ".connectionCheck";
        System.out.println(">> Checking elasticsearch connection:");
        do {
            try {
                pingElasticsearch();
                System.out.println("\t> Success");
                return;
            } catch (Exception e) {
                System.out.println("\t> Fail: " + ctx + ": " + e.getLocalizedMessage());
                if (retries == -1)
                    break;
                retries--;
                for (int i = 10; i > 0; i--) {
                    System.out.printf("\t> Retrying in: %1$s\r", i);
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception ex) {
                        throw new RuntimeException(ctx + ": " + ex.getLocalizedMessage());
                    }
                }
            }
        } while (retries > 0);
        throw new RuntimeException("Fail to establish connection with elasticsearch");
    }

    private void pingElasticsearch() {
        final String ctx = CLASSNAME + ".pingElasticsearch";
        try {
            String elasticHost = System.getenv(Constants.ENV_ELASTICSEARCH_HOST);
            String elasticPort = System.getenv(Constants.ENV_ELASTICSEARCH_PORT);

            Assert.hasText(elasticHost, "Missing elasticsearch host configuration value");
            Assert.hasText(elasticPort, "Missing elasticsearch port configuration value");

            final String ELASTIC_URL = String.format("http://%1$s:%2$s",
                System.getenv(Constants.ENV_ELASTICSEARCH_HOST), System.getenv(Constants.ENV_ELASTICSEARCH_PORT));

            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request rq = new Request.Builder().url(ELASTIC_URL).build();
            Response rs = client.newCall(rq).execute();
            Objects.requireNonNull(rs.body()).close();
            if (!rs.isSuccessful())
                throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException(ctx + ": " + e.getLocalizedMessage());
        }
    }
}
