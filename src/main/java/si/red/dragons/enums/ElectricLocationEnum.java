package si.red.dragons.enums;

import javax.persistence.Converter;

public enum ElectricLocationEnum implements AEnum<String> {

    CH("ch"),
    DE("de"),
    REST("rest"),
    AT("at"),
    CERTIFIED_GREEN("certified_green"),
    SE("se");


    private final String value;

    ElectricLocationEnum(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return null;
    }

    @Converter
    public static class JPAConverter extends AEnumConverter<String, ElectricLocationEnum> {
        public JPAConverter() {
            super(ElectricLocationEnum.class);
        }
    }

}
