package si.red.dragons.dtos;

import si.red.dragons.entity.Vehicle;

import java.time.LocalDateTime;

public class TransferDTO {
    private String startLocAddr;
    private String startLocCity;
    private String startLocPostalCode;
    private LocalDateTime startTime;
    private String endLocAddr;
    private String endLocCity;
    private String endLocPostalCode;
    private String points;
    private Vehicle vehicle;
    private String description;
    private Float price;

    public String getStartLocAddr() {
        return startLocAddr;
    }

    public String getStartLocCity() {
        return startLocCity;
    }

    public String getStartLocPostalCode() {
        return startLocPostalCode;
    }

    public String getStartLocation() {
        return startLocAddr + ", " + startLocCity + " " + startLocPostalCode;
    }

    public String getEndLocAddr() {
        return endLocAddr;
    }

    public String getEndLocCity() {
        return endLocCity;
    }

    public String getEndLocPostalCode() {
        return endLocPostalCode;
    }

    public String getEndLocation() {
        return endLocAddr + ", " + endLocCity + " " + endLocPostalCode;
    }

    public Float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Vehicle getVehicle() { return vehicle; }

    public void setStartLocAddr(String startLocAddr) {
        this.startLocAddr = startLocAddr;
    }

    public void setStartLocCity(String startLocCity) {
        this.startLocCity = startLocCity;
    }

    public void setStartLocPostalCode(String startLocPostalCode) {
        this.startLocPostalCode = startLocPostalCode;
    }

    public void setEndLocAddr(String endLocAddr) {
        this.endLocAddr = endLocAddr;
    }

    public void setEndLocCity(String endLocCity) {
        this.endLocCity = endLocCity;
    }

    public void setEndLocPostalCode(String endLocPostalCode) {
        this.endLocPostalCode = endLocPostalCode;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
