package endolabs.salesforce.bulkv2.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import endolabs.salesforce.bulkv2.type.ConcurrencyModeEnum;
import endolabs.salesforce.bulkv2.type.JobStateEnum;
import endolabs.salesforce.bulkv2.type.JobTypeEnum;
import endolabs.salesforce.bulkv2.type.LineEndingEnum;
import endolabs.salesforce.bulkv2.type.OperationEnum;
import lombok.Data;

@Data
public class JobInfo {

    private Double apiVersion;

    private String columnDelimiter;

    private ConcurrencyModeEnum concurrencyMode;

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

    private JobStateEnum state;

    private String systemModstamp;

    @JsonIgnore
    public boolean isFinished() {
        return state != null && state.isFinished();
    }
}
