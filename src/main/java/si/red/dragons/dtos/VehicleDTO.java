package si.red.dragons.dtos;

import si.red.dragons.enums.CarTypeEnum;
import si.red.dragons.enums.ElectricLocationEnum;

public class VehicleDTO {
    private CarTypeEnum cartype;
    private CarTypeEnum fuelType;
    private Float fuelConsumption;
    private ElectricLocationEnum electricLocationEnum;

    public CarTypeEnum getCartype() {
        return cartype;
    }

    public CarTypeEnum getFuelType() {
        return fuelType;
    }

    public Float getFuelConsumption() {
        return fuelConsumption;
    }

    public ElectricLocationEnum getElectricLocationEnum() {
        return electricLocationEnum;
    }

    public void setCartype(CarTypeEnum cartype) {
        this.cartype = cartype;
    }

    public void setFuelType(CarTypeEnum fuelType) {
        this.fuelType = fuelType;
    }

    public void setFuelConsumption(Float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public void setElectricLocationEnum(ElectricLocationEnum electricLocationEnum) {
        this.electricLocationEnum = electricLocationEnum;
    }
}
