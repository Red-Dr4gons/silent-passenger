package si.red.dragons.enums;

import javax.persistence.Converter;

public enum DeliveryStatusEnum implements AEnum<String> {

    PENDING("pending"),
    ACCEPTED("accepted"),
    REJECTED("rejected");

    private final String value;

    DeliveryStatusEnum(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return null;
    }

    @Converter
    public static class JPAConverter extends AEnumConverter<String, DeliveryStatusEnum> {
        public JPAConverter() {
            super(DeliveryStatusEnum.class);
        }
    }
}
