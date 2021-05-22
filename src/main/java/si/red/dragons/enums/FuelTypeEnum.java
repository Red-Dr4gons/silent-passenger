package si.red.dragons.enums;

import javax.persistence.Converter;

public enum FuelTypeEnum implements AEnum<String> {

    GAS("gas"),
    DIESEL("diesel"),
    HYBRID("hybrid"),
    BIOGAS("biogas"),
    NATURAL_GAS("naturalgas"),
    ELECTRIC("electric"),
    BIODIESEL("biodiesel"),
    ETHANOL_10("ethanol_10"),
    ETHANOL_85("ethanol_85"),
    PLUG_IN_HYBRID("plug_in_hybrid");

    private final String value;

    FuelTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Converter
    public static class JPAConverter extends AEnumConverter<String, FuelTypeEnum> {
        public JPAConverter() {
            super(FuelTypeEnum.class);
        }
    }
}
