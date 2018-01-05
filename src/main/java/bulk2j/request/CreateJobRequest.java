package bulk2j.request;

import bulk2j.Bulk2Client;
import bulk2j.RestRequester;
import bulk2j.response.CreateJobResponse;
import bulk2j.type.ColumnDelimiterEnum;
import bulk2j.type.LineEndingEnum;
import bulk2j.type.OperationEnum;
import lombok.Value;

@Value
public class CreateJobRequest {

    private final ColumnDelimiterEnum columnDelimiter;

    private final String contentType;

    private final String externalIdFieldName;

    private final LineEndingEnum lineEnding;

    private final String object;

    private final OperationEnum operation;

    public static class Builder {

        private final Bulk2Client client;

        private final String url;

        private String object;

        private OperationEnum operation;

        private String contentType;

        public Builder(Bulk2Client client, String url) {
            this.client = client;
            this.url = url;
        }

        public Builder withObject(String object) {
            this.object = object;
            return this;
        }

        public Builder withOperation(OperationEnum operation) {
            this.operation = operation;
            return this;
        }

        public Builder withContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public CreateJobResponse execute() {
            CreateJobRequest request = new CreateJobRequest(null, contentType, null, null, object, operation);

            RestRequester requester = new RestRequester(client.getClient());

            return requester.post(url, request, CreateJobResponse.class);
        }
    }
}
