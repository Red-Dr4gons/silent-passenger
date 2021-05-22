package si.red.dragons.enums;

import javax.persistence.Converter;

public enum CarTypeEnum implements AEnum<String> {

    SMALL("small"),
    MIDSIZE("midsize"),
    LUXURY("luxury_suv_van");

    private final String value;

    CarTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Converter
    public static class JPAConverter extends AEnumConverter<String, CarTypeEnum> {
        public JPAConverter() {
            super(CarTypeEnum.class);
        }
    }

}
