package pl.desz.vehiclemarket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import pl.desz.vehiclemarket.elastic.ElasticClient;
import pl.desz.vehiclemarket.model.Person;
import pl.desz.vehiclemarket.vehicle.VehicleIndex;
import pl.desz.vehiclemarket.vehicle.VehicleService;
import pl.desz.vehiclemarket.vehicle.VehicleServiceImpl;
import pl.desz.vehiclemarket.vehicle.VehicleType;

import java.math.BigDecimal;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException, JsonProcessingException {

        TransportClient tc = ElasticClient.getClient("192.168.99.100", 9300);

        VehicleIndex vehicleIndex = new VehicleIndex("opel", "astra", true,
                false, new BigDecimal(9500d), "cool car, buy it",
                new Person("denis", "den", "123456"), VehicleType.CAR);

        VehicleService service = new VehicleServiceImpl(tc);
        RestStatus response = service.save(vehicleIndex);

        System.out.println("response = " + response);

        tc.close();
    }
}
