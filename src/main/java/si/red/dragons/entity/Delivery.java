package si.red.dragons.entity;

import si.red.dragons.enums.DeliveryStatusEnum;

import javax.persistence.*;

@Entity
@Table(name = "DELIVERY")
public class Delivery {

    @Id
    @Column(name = "ID_DELIVERY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDelivery;

    @Column(name = "START_LOCATION")
    private String startLocation;

    @Column(name = "END_LOCATION")
    private String endLocation;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    @Convert(converter = DeliveryStatusEnum.JPAConverter.class)
    private DeliveryStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
    @JoinColumn(name = "ID_ACCOUNT")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Transfer.class)
    @JoinColumn(name = "ID_TRANSFER")
    private Transfer transfer;


    public Long getIdDelivery() {
        return idDelivery;
    }

    public void setIdDelivery(Long idDelivery) {
        this.idDelivery = idDelivery;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public DeliveryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatusEnum status) {
        this.status = status;
    }

    public Transfer getTransfer() {
        return transfer;
    }

    public void setTransfer(Transfer transfer) {
        this.transfer = transfer;
    }
}