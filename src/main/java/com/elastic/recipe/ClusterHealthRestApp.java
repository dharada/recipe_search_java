package com.elastic.recipe;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClusterHealthRestApp {

    public static void main(String[] args) throws UnknownHostException {

        TransportClient client = null;

        try {
            // create client for localhost es
            client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("34.85.93.243"), 9300));

            ClusterHealthResponse clusterHealthResponse =
                    client.admin().cluster().health(new ClusterHealthRequest()).actionGet();

            System.out.println(clusterHealthResponse.getStatus().name());

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
