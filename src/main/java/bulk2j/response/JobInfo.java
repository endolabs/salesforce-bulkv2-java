package bulk2j.response;

import bulk2j.type.ConcurrencyModeEnum;
import bulk2j.type.JobTypeEnum;
import bulk2j.type.LineEndingEnum;
import bulk2j.type.OperationEnum;
import lombok.Data;

@Data
public class JobInfo {

    private Double apiVersion;

    private String columnDelimiter; // "CONMA"

    private ConcurrencyModeEnum concurrencyMode; // "Parallel"

    private String contentType;

    private String contentUrl;

    private String createdById;

    private String createdDate; // such as "2018-01-05T17:52:38.000+0000"

    private String externalIdFieldName;

    private String id;

    private JobTypeEnum jobType;

    private LineEndingEnum lineEnding;

    private String object;

    private OperationEnum operation;

    private String state; // "Open"

    private String systemModstamp;
}
