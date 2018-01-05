package bulk2j.request;

import bulk2j.RestRequester;
import bulk2j.response.CloseOrAbortJobResponse;
import bulk2j.type.JobStateEnum;
import lombok.Value;

@Value
public class CloseOrAbortJobRequest {

    private final JobStateEnum state;

    private CloseOrAbortJobRequest(Builder builder) {
        this.state = builder.state;
    }

    public static class Builder {

        private final RestRequester requester;

        private final String url;

        // parameters

        private JobStateEnum state;

        public Builder(RestRequester requester, String url, JobStateEnum state) {
            this.requester = requester;
            this.url = url;
            this.state = state;
        }

        public CloseOrAbortJobResponse execute() {
            CloseOrAbortJobRequest request = new CloseOrAbortJobRequest(this);

            return requester.patch(url, request, CloseOrAbortJobResponse.class);
        }
    }
}
