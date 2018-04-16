package pl.desz.vehiclemarket.domain.vehicle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import sun.security.provider.certpath.OCSPResponse;

import java.io.IOException;
import java.util.Optional;

public class VehicleServiceImpl implements VehicleService {

    public static final String INDEX = "vehicle";
    public static final String TYPE = "vehicle";
    public static final int TIMEOUT_IN_SECONDS = 10;

    private TransportClient client;
    private ObjectMapper mapper;

    public VehicleServiceImpl(TransportClient client) {
        this(client, null);
    }

    public VehicleServiceImpl(TransportClient client, ObjectMapper mapper) {
        this.client = client;

        if (mapper == null) {
            ObjectMapper newMapper = new ObjectMapper();
            newMapper.registerModule(new JavaTimeModule());
            this.mapper = newMapper;
        }
    }

    @Override
    public Optional<VehicleIndex> findById(String id) {
        GetResponse doc = client.prepareGet(INDEX, TYPE, id)
                .get(TimeValue.timeValueSeconds(TIMEOUT_IN_SECONDS));
        try {
            return Optional.of(mapper.readValue(doc.getSourceAsString(), VehicleIndex.class));
        } catch (IOException e) {
            throw new RuntimeException("Could not parse response JSON.", e);
        }
    }

    @Override
    public Optional<String> save(VehicleIndex vehicleIndex) {
        IndexResponse indexResponse = client.prepareIndex(INDEX, TYPE)
                .setSource(convertToJson(vehicleIndex), XContentType.JSON)
                .get(TimeValue.timeValueSeconds(TIMEOUT_IN_SECONDS));

        return Optional.ofNullable(indexResponse.getId());
    }

    @Override
    public boolean update(VehicleIndex vehicleIndex) {
        UpdateResponse response = client.prepareUpdate(INDEX, TYPE, vehicleIndex.getId())
                .setDoc(vehicleIndex)
                .get();
        return response.status() == RestStatus.OK;
    }

    @Override
    public boolean delete(VehicleIndex vehicleIndex) {

        DeleteResponse response = client.prepareDelete(INDEX, TYPE, vehicleIndex.getId())
                .get();

        return response.status() == RestStatus.OK;
    }

    private String convertToJson(VehicleIndex vehicle) {
        try {
            return mapper.writeValueAsString(vehicle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert object to JSON", e);
        }
    }
}
