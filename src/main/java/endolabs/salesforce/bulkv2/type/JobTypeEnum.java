package endolabs.salesforce.bulkv2.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum JobTypeEnum {

    BIG_OBJECTS("BigObjectIngest"),

    BULK_API_1_0("Classic"),

    BULK_API_2_0("V2Ingest");

    private final String value;

    JobTypeEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String toJsonValue() {
        return value;
    }

    @JsonCreator
    public static JobTypeEnum fromValue(String value) {
        return Arrays.stream(values())
                .filter(v -> v.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(value)));
    }
}
