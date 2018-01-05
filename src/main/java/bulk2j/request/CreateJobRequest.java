package bulk2j.request;

import bulk2j.type.ColumnDelimiterEnum;
import bulk2j.type.LineEndingEnum;
import bulk2j.type.OperationEnum;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreateJobRequest {

    private final ColumnDelimiterEnum columnDelimiter;

    private final String contentType;

    private final String externalIdFieldName;

    private final LineEndingEnum lineEnding;

    private final String object;

    private final OperationEnum operation;

    public CreateJobRequest(String object, OperationEnum operation) {
        this(null, null, null, null, object, operation);
    }

    public CreateJobRequest(String object, OperationEnum operation, String contentType) {
        this(null, contentType, null, null, object, operation);
    }
}
