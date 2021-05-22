package si.red.dragons.dtos;

import si.red.dragons.enums.FuelTypeEnum;

public class VehicleDTO {
    private String plate;
    private FuelTypeEnum fuelType;
    private Float fuelConsumption;

    public String getPlate() {
        return plate;
    }

    public FuelTypeEnum getFuelType() {
        return fuelType;
    }

    public Float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setFuelType(FuelTypeEnum fuelType) {
        this.fuelType = fuelType;
    }

    public void setFuelConsumption(Float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
}
