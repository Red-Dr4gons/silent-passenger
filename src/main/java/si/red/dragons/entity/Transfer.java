package si.red.dragons.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "TRANSFER")
public class Transfer extends PanacheEntityExtended {

    @Id
    @Column(name = "ID_TRANSFER")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransfer;

    @Column(name = "START_LOCATION")
    private String startLocation;

    @Column(name = "END_LOCATION")
    private String endLocation;

    @Column(length = 65535, columnDefinition = "Text", name = "POINTS")
    private String points;

    @Column(name = "PRICE")
    private Float price;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @JsonbTransient
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ACCOUNT")
    private Account account;

    @JsonbTransient
    @Column(name = "DELIVERIES")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transfer", cascade = CascadeType.ALL)
    private Set<Delivery> deliveries;

    public Long getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(Long idTransfer) {
        this.idTransfer = idTransfer;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getStartLocAddr() {
        return startLocation.split(",")[0];
    }

    public String getStartLocCity() {
        String cityAndCode = startLocation.split(",")[1];
        String city = "";
        for(String token : cityAndCode.split(" ")) {
            city += token + " ";
        }
        return city;
    }

    public String getStartLocPostalCode() {
        String cityAndCode = startLocation.split(",")[1];
        int numOfSplits = cityAndCode.split(" ").length;
        String code = cityAndCode.split(" ")[numOfSplits - 1];
        return code;
    }

    public String getEndLocAddr() {
        return endLocation.split(",")[0];
    }

    public String getEndLocCity() {
        String cityAndCode = endLocation.split(",")[1];
        String city = "";
        for(String token : cityAndCode.split(" ")) {
            city += token + " ";
        }
        return city;
    }

    public String getEndLocPostalCode() {
        String cityAndCode = endLocation.split(",")[1];
        int numOfSplits = cityAndCode.split(" ").length;
        String code = cityAndCode.split(" ")[numOfSplits - 1];
        return code;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(Set<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
