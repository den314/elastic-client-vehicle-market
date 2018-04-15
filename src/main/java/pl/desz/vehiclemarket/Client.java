package pl.desz.vehiclemarket;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.elasticsearch.client.transport.TransportClient;
import pl.desz.vehiclemarket.elastic.ElasticClient;
import pl.desz.vehiclemarket.person.Person;
import pl.desz.vehiclemarket.vehicle.VehicleIndex;
import pl.desz.vehiclemarket.vehicle.VehicleService;
import pl.desz.vehiclemarket.vehicle.VehicleServiceImpl;
import pl.desz.vehiclemarket.vehicle.VehicleType;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.Optional;

public class Client {

    public static void main(String[] args) throws UnknownHostException, JsonProcessingException {
        // test drive
        saveAndFind();
    }

    private static void saveAndFind() throws UnknownHostException {
        TransportClient tc = ElasticClient.getClient("192.168.99.100", 9300);

        VehicleIndex vehicleIndex = new VehicleIndex("opel", "astra", true,
                false, new BigDecimal(9500d), "cool car, buy it",
                new Person("denis", "den", "123456"), VehicleType.CAR);

        VehicleService service = new VehicleServiceImpl(tc);
        Optional<String> save = service.save(vehicleIndex);

        System.out.println("response = " + save.orElse("id not found, save failed?"));

        save.ifPresent(id -> {
            Optional<VehicleIndex> byId = service.findById(id);

            byId.ifPresent(System.out::println);
        });

        tc.close();
    }
}
