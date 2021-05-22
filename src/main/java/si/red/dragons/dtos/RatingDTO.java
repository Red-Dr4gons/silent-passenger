package si.red.dragons.dtos;

import si.red.dragons.entity.Account;

public class RatingDTO {
    private Integer value;
    private Integer comment;
    private Account account;

    public Integer getValue() {
        return value;
    }

    public Integer getComment() {
        return comment;
    }

    public Account getAccount() {
        return account;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
