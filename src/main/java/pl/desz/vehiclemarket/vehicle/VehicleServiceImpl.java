package pl.desz.vehiclemarket.vehicle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;

public class VehicleServiceImpl implements VehicleService {

    public static final String INDEX = "vehicle";
    public static final String TYPE = "vehicle";

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
    public RestStatus findById(String id) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public RestStatus save(VehicleIndex vehicleIndex) {
        IndexResponse indexResponse = client.prepareIndex(INDEX, TYPE)
                .setSource(convertToJson(vehicleIndex), XContentType.JSON)
                .get();

        return indexResponse.status();
    }

    @Override
    public RestStatus update(VehicleIndex vehicleIndex) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public boolean delete(VehicleIndex vehicleIndex) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    private String convertToJson(VehicleIndex vehicle) {
        try {
            return mapper.writeValueAsString(vehicle);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert object to JSON", e);
        }
    }
}
