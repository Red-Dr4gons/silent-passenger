package si.red.dragons.entity;

import si.red.dragons.enums.CarTypeEnum;
import si.red.dragons.enums.ElectricLocationEnum;
import si.red.dragons.enums.FuelTypeEnum;

import javax.persistence.*;

@Entity
public class Vehicle extends PanacheEntityExtended {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicle;
    @Convert(converter = CarTypeEnum.JPAConverter.class)
    private CarTypeEnum carType;
    @Convert(converter = FuelTypeEnum.JPAConverter.class)
    private CarTypeEnum fuelType;
    private Float fuelConsumption;
    @Convert(converter = ElectricLocationEnum.JPAConverter.class)
    private ElectricLocationEnum electricLocationEnum;
    @ManyToOne
    private User idUser;

    public Long getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Long idVehicle) {
        this.idVehicle = idVehicle;
    }

    public CarTypeEnum getCarType() {
        return carType;
    }

    public void setCarType(CarTypeEnum carType) {
        this.carType = carType;
    }

    public CarTypeEnum getFuelType() {
        return fuelType;
    }

    public void setFuelType(CarTypeEnum fuelType) {
        this.fuelType = fuelType;
    }

    public Float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public ElectricLocationEnum getElectricLocationEnum() {
        return electricLocationEnum;
    }

    public void setElectricLocationEnum(ElectricLocationEnum electricLocationEnum) {
        this.electricLocationEnum = electricLocationEnum;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }
}
