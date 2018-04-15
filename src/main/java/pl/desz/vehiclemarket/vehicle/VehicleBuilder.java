package pl.desz.vehiclemarket.vehicle;

import pl.desz.vehiclemarket.person.Person;

import java.math.BigDecimal;
import java.util.Optional;

public class VehicleBuilder {

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

    private boolean usedNotSet = true;
    private boolean damagedNotSet = true;

    public Optional<VehicleIndex> build() {
        boolean valid = areMandatoryFieldsSet();
        return valid ? Optional.of(new VehicleIndex(this.mark, this.model, this.used, this.damaged, this.price, this.description, this.seller, this.vehicleType))
                : Optional.empty();
    }

    private boolean areMandatoryFieldsSet() {
        return this.model != null && this.mark != null && !this.usedNotSet && !this.damagedNotSet
                && this.price != null && this.description != null && !this.description.trim().isEmpty() && this.seller != null
                && this.vehicleType != null;
    }

    public VehicleBuilder setMark(String mark) {
        this.mark = mark;
        return this;
    }

    public VehicleBuilder setModel(String model) {
        this.model = model;
        return this;
    }

    public VehicleBuilder setMileage(String mileage) {
        this.mileage = mileage;
        return this;
    }

    public VehicleBuilder setProductionYear(int productionYear) {
        this.productionYear = productionYear;
        return this;
    }

    public VehicleBuilder setUsed(boolean used) {
        this.usedNotSet = false;
        this.used = used;
        return this;
    }

    public VehicleBuilder setDamaged(boolean damaged) {
        this.damagedNotSet = false;
        this.damaged = damaged;
        return this;
    }

    public VehicleBuilder setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public VehicleBuilder setResideCity(String resideCity) {
        this.resideCity = resideCity;
        return this;
    }

    public VehicleBuilder setResideCountry(String resideCountry) {
        this.resideCountry = resideCountry;
        return this;
    }

    public VehicleBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public VehicleBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public VehicleBuilder setSeller(Person seller) {
        this.seller = seller;
        return this;
    }

}
