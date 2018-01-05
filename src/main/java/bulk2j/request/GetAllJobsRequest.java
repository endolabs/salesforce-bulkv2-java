package bulk2j.request;

import bulk2j.RestRequester;
import bulk2j.response.GetAllJobsResponse;
import bulk2j.type.ConcurrencyModeEnum;
import lombok.Value;

@Value
public class GetAllJobsRequest {

    private final ConcurrencyModeEnum concurrencyMode;

    private GetAllJobsRequest(Builder builder) {
        this.concurrencyMode = builder.concurrencyMode;
    }

    public static class Builder {

        private final RestRequester requester;

        private final String url;

        private ConcurrencyModeEnum concurrencyMode;

        public Builder(RestRequester requester, String url) {
            this.requester = requester;
            this.url = url;
        }

        public Builder withConcurrencyMode(ConcurrencyModeEnum concurrencyMode) {
            this.concurrencyMode = concurrencyMode;
            return this;
        }

        public GetAllJobsResponse execute() {
            return requester.get(url, GetAllJobsResponse.class);
        }
    }
}
