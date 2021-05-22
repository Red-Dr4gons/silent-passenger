package si.red.dragons.enums;

import javax.persistence.AttributeConverter;
import java.util.Optional;

public interface AEnum<T> {
    T value();

    static <T, E extends AEnum<T>> T valueOrNull(E enumInstance) {
        return Optional.ofNullable(enumInstance).map(AEnum::value).orElse(null);
    }

    static <T, E extends AEnum<T>> E valueOf(Class<E> enumClass, T value) {
        if (value == null) {
            return null;
        }
        E[] enums = enumClass.getEnumConstants();
        for (E e : enums) {
            if (e.value().equals(value)) {
                return e;
            }
        }
        return null;
    }

    class AEnumConverter<T, E extends AEnum<T>> implements AttributeConverter<E, T> {
        private final Class<E> enumClass;

        public AEnumConverter(Class<E> enumClass) {
            this.enumClass = enumClass;
        }

        @Override
        public T convertToDatabaseColumn(E attribute) {
            return attribute != null ? attribute.value() : null;
        }

        @Override
        public E convertToEntityAttribute(T dbData) {
            return AEnum.valueOf(enumClass, dbData);
        }
    }

}
