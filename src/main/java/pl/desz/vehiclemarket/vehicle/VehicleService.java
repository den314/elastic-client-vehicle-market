package pl.desz.vehiclemarket.vehicle;

import org.elasticsearch.rest.RestStatus;

public interface VehicleService {

    RestStatus findById(String id);
    RestStatus save(VehicleIndex vehicleIndex);
    RestStatus update(VehicleIndex vehicleIndex);
    boolean delete(VehicleIndex vehicleIndex);
}
