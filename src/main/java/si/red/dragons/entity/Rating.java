package si.red.dragons.entity;

import javax.persistence.*;

@Entity
public class Rating extends PanacheEntityExtended {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRating;
    private Integer value;
    private Integer comment;
    
    @ManyToOne
    private User idUser;

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

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }
}
