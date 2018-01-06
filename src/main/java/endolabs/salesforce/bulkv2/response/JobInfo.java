package endolabs.salesforce.bulkv2.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import endolabs.salesforce.bulkv2.type.ColumnDelimiterEnum;
import endolabs.salesforce.bulkv2.type.ConcurrencyModeEnum;
import endolabs.salesforce.bulkv2.type.JobStateEnum;
import endolabs.salesforce.bulkv2.type.JobTypeEnum;
import endolabs.salesforce.bulkv2.type.LineEndingEnum;
import endolabs.salesforce.bulkv2.type.OperationEnum;

public class JobInfo {

    private Double apiVersion;

    private ColumnDelimiterEnum columnDelimiter;

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

    public Double getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(Double apiVersion) {
        this.apiVersion = apiVersion;
    }

    public ColumnDelimiterEnum getColumnDelimiter() {
        return columnDelimiter;
    }

    public void setColumnDelimiter(ColumnDelimiterEnum columnDelimiter) {
        this.columnDelimiter = columnDelimiter;
    }

    public ConcurrencyModeEnum getConcurrencyMode() {
        return concurrencyMode;
    }

    public void setConcurrencyMode(ConcurrencyModeEnum concurrencyMode) {
        this.concurrencyMode = concurrencyMode;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getExternalIdFieldName() {
        return externalIdFieldName;
    }

    public void setExternalIdFieldName(String externalIdFieldName) {
        this.externalIdFieldName = externalIdFieldName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JobTypeEnum getJobType() {
        return jobType;
    }

    public void setJobType(JobTypeEnum jobType) {
        this.jobType = jobType;
    }

    public LineEndingEnum getLineEnding() {
        return lineEnding;
    }

    public void setLineEnding(LineEndingEnum lineEnding) {
        this.lineEnding = lineEnding;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public OperationEnum getOperation() {
        return operation;
    }

    public void setOperation(OperationEnum operation) {
        this.operation = operation;
    }

    public JobStateEnum getState() {
        return state;
    }

    public void setState(JobStateEnum state) {
        this.state = state;
    }

    public String getSystemModstamp() {
        return systemModstamp;
    }

    public void setSystemModstamp(String systemModstamp) {
        this.systemModstamp = systemModstamp;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "apiVersion=" + getApiVersion() +
                ", columnDelimiter=" + getColumnDelimiter() +
                ", concurrencyMode=" + getConcurrencyMode() +
                ", contentType=" + getContentType() +
                ", contentUrl=" + getContentUrl() +
                ", createdById=" + getCreatedById() +
                ", createdDate=" + getCreatedDate() +
                ", externalIdFieldName=" + getExternalIdFieldName() +
                ", id=" + getId() +
                ", jobType=" + getJobType() +
                ", lineEnding=" + getLineEnding() +
                ", object=" + getObject() +
                ", operation=" + getOperation() +
                ", state=" + getState() +
                ", systemModstamp=" + getSystemModstamp() +
                '}';
    }
}
