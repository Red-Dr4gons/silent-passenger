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
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
    @JoinColumn(name = "ID_ACCOUNT_TO", referencedColumnName = "ID_ACCOUNT")
    private Account accountTo;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Account.class)
    @JoinColumn(name = "ID_ACCOUNT_FROM", referencedColumnName = "ID_ACCOUNT")
    private Account accountFrom;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account account) {
        this.accountTo = account;
    }

    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }
}
