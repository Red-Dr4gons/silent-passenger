package si.red.dragons.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
public class Account extends PanacheEntityExtended implements Serializable {

    @Id
    @Column(name = "ID_ACCOUNT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURNAME")
    private String surname;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "RATINGS")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountTo", cascade = CascadeType.ALL)
    private Set<Rating> ratings;

    @Column(name = "VEHICLES")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Vehicle> vehicles;

    @JsonbTransient
    @Column(name = "TRANSFERS")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Transfer> transfers;

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idUser) {
        this.idAccount = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(Set<Transfer> transfers) {
        this.transfers = transfers;
    }
}
