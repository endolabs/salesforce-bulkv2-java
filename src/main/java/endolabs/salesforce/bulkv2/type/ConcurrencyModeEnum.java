package endolabs.salesforce.bulkv2.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum ConcurrencyModeEnum {

    PARALLEL("Parallel"),

    SERIAL("Serial");

    private final String value;

    ConcurrencyModeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String toJsonValue() {
        return value;
    }

    @JsonCreator
    public static ConcurrencyModeEnum fromValue(String value) {
        return Arrays.stream(values())
                .filter(v -> v.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(value)));
    }
}
