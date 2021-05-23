package si.red.dragons.dtos;

import si.red.dragons.enums.DeliveryStatusEnum;

import java.time.LocalDateTime;

public class DeliveryDTO {
    private String startLocAddr;
    private String startLocCity;
    private String startLocPostalCode;
    private String endLocAddr;
    private String endLocCity;
    private String endLocPostalCode;
    private String description;
    private DeliveryStatusEnum status;
    private String transferId;
    private float emissionsSaved;

    public float getEmissionsSaved() {
        return emissionsSaved;
    }

    public void setEmissionsSaved(float emissionsSaved) {
        this.emissionsSaved = emissionsSaved;
    }

    public String getStartLocation() {
        return startLocAddr + ", " + startLocCity + " " + startLocPostalCode;
    }

    public String getEndLocation() {
        return endLocAddr + ", " + endLocCity + " " + endLocPostalCode;
    }

    public String getStartLocAddr() {
        return startLocAddr;
    }

    public void setStartLocAddr(String startLocAddr) {
        this.startLocAddr = startLocAddr;
    }

    public String getStartLocCity() {
        return startLocCity;
    }

    public void setStartLocCity(String startLocCity) {
        this.startLocCity = startLocCity;
    }

    public String getStartLocPostalCode() {
        return startLocPostalCode;
    }

    public void setStartLocPostalCode(String startLocPostalCode) {
        this.startLocPostalCode = startLocPostalCode;
    }

    public String getEndLocAddr() {
        return endLocAddr;
    }

    public void setEndLocAddr(String endLocAddr) {
        this.endLocAddr = endLocAddr;
    }

    public String getEndLocCity() {
        return endLocCity;
    }

    public void setEndLocCity(String endLocCity) {
        this.endLocCity = endLocCity;
    }

    public String getEndLocPostalCode() {
        return endLocPostalCode;
    }

    public void setEndLocPostalCode(String endLocPostalCode) {
        this.endLocPostalCode = endLocPostalCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeliveryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatusEnum status) {
        this.status = status;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }
}
