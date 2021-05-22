package si.red.dragons.entity;

import javax.persistence.*;

@Entity
public class Rating extends PanacheEntityExtended {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRating;
    private Integer value;
    private Integer comment;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
    @JoinColumn(name = "idAccount")
    private Account account;

    public Long getIdRating() {
        return idRating;
    }

    public void setIdRating(Long idRating) {
        this.idRating = idRating;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
