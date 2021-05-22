package si.red.dragons.entity;

import si.red.dragons.enums.CarTypeEnum;
import si.red.dragons.enums.ElectricLocationEnum;
import si.red.dragons.enums.FuelTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLE")
public class Vehicle extends PanacheEntityExtended {

    @Id
    @Column(name = "ID_VEHICLE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicle;

    @Column(name = "CAR_TYPE")
    @Convert(converter = CarTypeEnum.JPAConverter.class)
    private CarTypeEnum carType;

    @Column(name = "FUEL_TYPE")
    @Convert(converter = FuelTypeEnum.JPAConverter.class)
    private CarTypeEnum fuelType;

    @Column(name = "FUEL_CONSUMPTION")
    private Float fuelConsumption;

    @Column(name = "ELECTRIC_LOCATION")
    @Convert(converter = ElectricLocationEnum.JPAConverter.class)
    private ElectricLocationEnum electricLocationEnum;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
    @JoinColumn(name = "ID_ACCOUNT")
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
