package pl.desz.vehiclemarket.elastic;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Singleton class for getting Elastic client instance.
 * Exposes {@link #getClient(String, int)} method to get the instance.
 */
public final class ElasticClient {

    private static TransportClient client;

    /**
     * Returns elastic client that can be used to interact
     * with elasticsearch instance programmatically.
     * @param host elasticsearch server hostname
     * @param port elasticsearch transport port
     *
     * @return elasticsearch client instance
     * @throws UnknownHostException if elastic host is not valid
     */
    public static TransportClient getClient(String host, int port) throws UnknownHostException {
        if (client == null) {
                client = new PreBuiltTransportClient(getSettings())
                        .addTransportAddress(new TransportAddress(InetAddress.getByName(host), port));
        }
        return client;
    }

    private static Settings getSettings() {
        return Settings.builder()
                .put("cluster.name", "elasticsearch")
                .build();
    }
}
