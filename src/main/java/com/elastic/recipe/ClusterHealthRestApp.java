package com.elastic.recipe;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.action.main.MainResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class ClusterHealthRestApp {

    public static void main(String[] args) throws Exception {

        RestHighLevelClient client = null;

        try {

            RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
//            RestClientBuilder builder = RestClient.builder(new HttpHost("34.85.93.243", 9200, "http"));
            builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                @Override
                public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                    return requestConfigBuilder.setSocketTimeout(-1).setConnectionRequestTimeout(-1);
                }
            });

//            RestClient.builder(
//                    new HttpHost("34.85.93.243", 9200, "http"));

            client = new RestHighLevelClient(builder);


            MainResponse response = client.info();


            System.out.println("cluster version=" + response.getVersion());

        } catch (IOException e) {
            throw e;
        } finally {
            try {
                client.close();
            } catch (Exception ex){
                //ignore
            }
        }
    }
}
