package si.red.dragons.enums;

public enum ResponseStatus {
    SUCCESS,
    FAILURE;

    public String value() {
        return name();
    }

    public static ResponseStatus fromValue(String v) {
        return valueOf(v);
    }
}
