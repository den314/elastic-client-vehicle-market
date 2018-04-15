package pl.desz.vehiclemarket.vehicle;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import pl.desz.vehiclemarket.person.Person;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class VehicleIndex {
    private String mark;
    private String model;
    private String mileage;
    private int productionYear;
    private boolean used;
    private boolean damaged;
    private FuelType fuelType;
    private String resideCity;
    private String resideCountry;
    private BigDecimal price;
    private String description;
    private Person seller;
    private VehicleType vehicleType;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private final LocalDateTime addedDateTime = LocalDateTime.now();

    public VehicleIndex(String mark, String model, boolean used, boolean damaged, BigDecimal price,
                        String description, Person seller, VehicleType vehicleType) {
        this.mark = mark;
        this.model = model;
        this.used = used;
        this.damaged = damaged;
        this.price = price;
        this.description = description;
        this.seller = seller;
        this.vehicleType = vehicleType;
    }

}
