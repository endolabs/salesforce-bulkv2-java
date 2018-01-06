package endolabs.salesforce.bulkv2.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import endolabs.salesforce.bulkv2.type.ColumnDelimiterEnum;
import endolabs.salesforce.bulkv2.type.LineEndingEnum;
import endolabs.salesforce.bulkv2.type.OperationEnum;
import lombok.Value;

import java.io.File;

@Value
public class CreateJobRequest {

    private final ColumnDelimiterEnum columnDelimiter;

    private final String contentType;

    private final String externalIdFieldName;

    private final LineEndingEnum lineEnding;

    private final String object;

    private final OperationEnum operation;

    @JsonIgnore
    private final String content;

    @JsonIgnore
    private final File contentFile;

    private CreateJobRequest(Builder builder) {
        this.columnDelimiter = builder.columnDelimiter;
        this.contentType = builder.contentType;
        this.externalIdFieldName = builder.externalIdFieldName;
        this.lineEnding = builder.lineEnding;
        this.object = builder.object;
        this.operation = builder.operation;
        this.content = builder.content;
        this.contentFile = builder.contentFile;
    }

    public static class Builder {

        private String object;

        private OperationEnum operation;

        private ColumnDelimiterEnum columnDelimiter;

        private String contentType;

        private String externalIdFieldName;

        private LineEndingEnum lineEnding;

        private String content;

        private File contentFile;

        public Builder(String object, OperationEnum operation) {
            this.object = object;
            this.operation = operation;
        }

        public Builder withColumnDelimiter(ColumnDelimiterEnum columnDelimiter) {
            this.columnDelimiter = columnDelimiter;
            return this;
        }

        public Builder withContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder withExternalIdFieldName(String externalIdFieldName) {
            this.externalIdFieldName = externalIdFieldName;
            return this;
        }

        public Builder withLineEnding(LineEndingEnum lineEnding) {
            this.lineEnding = lineEnding;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withContent(File file) {
            this.contentFile = contentFile;
            return this;
        }

        public CreateJobRequest build() {
            return new CreateJobRequest(this);
        }
    }
}
