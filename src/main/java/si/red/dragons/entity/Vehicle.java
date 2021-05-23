package si.red.dragons.entity;

import si.red.dragons.enums.FuelTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLE")
public class Vehicle extends PanacheEntityExtended {

    @Id
    @Column(name = "ID_VEHICLE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehicle;

    @Column(name = "PLATE")
    private String plate;

    @Column(name = "FUEL_TYPE")
    @Convert(converter = FuelTypeEnum.JPAConverter.class)
    private FuelTypeEnum fuelType;

    @Column(name = "FUEL_CONSUMPTION")
    private Float fuelConsumption;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
    @JoinColumn(name = "ID_ACCOUNT")
    private Account account;

    public Long getIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(Long idVehicle) {
        this.idVehicle = idVehicle;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public FuelTypeEnum getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeEnum fuelType) {
        this.fuelType = fuelType;
    }

    public Float getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
