package pl.desz.vehiclemarket.vehicle;

import java.util.Optional;

public interface VehicleService {

    Optional<VehicleIndex> findById(String id);
    Optional<String> save(VehicleIndex vehicleIndex);
    boolean update(VehicleIndex vehicleIndex);
    boolean delete(VehicleIndex vehicleIndex);
}
