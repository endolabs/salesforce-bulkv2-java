package endolabs.salesforce.bulkv2.request;

import endolabs.salesforce.bulkv2.RestRequester;
import endolabs.salesforce.bulkv2.response.GetAllJobsResponse;
import endolabs.salesforce.bulkv2.type.ConcurrencyModeEnum;
import endolabs.salesforce.bulkv2.type.JobTypeEnum;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
public class GetAllJobsRequest {

    public static class Builder {

        private final RestRequester requester;

        private final String url;

        // parameters

        private ConcurrencyModeEnum concurrencyMode;

        private boolean isPkChunkingEnabled;

        private JobTypeEnum jobType;

        private String queryLocator;

        public Builder(RestRequester requester, String url) {
            this.requester = requester;
            this.url = url;
        }

        public Builder withConcurrencyMode(ConcurrencyModeEnum concurrencyMode) {
            this.concurrencyMode = concurrencyMode;
            return this;
        }

        public Builder pkChunkingEnabled() {
            this.isPkChunkingEnabled = true;
            return this;
        }

        public Builder withJobType(JobTypeEnum jobType) {
            this.jobType = jobType;
            return this;
        }

        public Builder withQueryLocator(String queryLocator) {
            this.queryLocator = queryLocator;
            return this;
        }

        public GetAllJobsResponse execute() {
            Map<String, String> queryParams = new HashMap<>();
            if (concurrencyMode != null) {
                queryParams.put("concurrencyMode", concurrencyMode.toJsonValue());
            }
            if (isPkChunkingEnabled) {
                queryParams.put("isPkChunkingEnabled", "true");
            }
            if (jobType != null) {
                queryParams.put("jobType", jobType.toJsonValue());
            }
            if (queryLocator != null) {
                queryParams.put("queryLocator", queryLocator);
            }

            return requester.get(url, queryParams, GetAllJobsResponse.class);
        }
    }
}
