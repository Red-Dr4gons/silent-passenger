package si.red.dragons.entity;

import javax.persistence.*;

@Entity
@Table(name = "RATING")
public class Rating extends PanacheEntityExtended {

    @Id
    @Column(name = "ID_RATING")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRating;

    @Column(name = "VALUE")
    private Integer value;

    @Column(name = "COMMNET")
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
