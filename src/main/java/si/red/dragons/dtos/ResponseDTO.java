package si.red.dragons.dtos;


import si.red.dragons.enums.ResponseStatus;

public class ResponseDTO {
    private ResponseStatus status;
    private String message;

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
