package bulk2j;

import bulk2j.request.CreateJobRequest;
import bulk2j.type.OperationEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

@Slf4j
@RequiredArgsConstructor
public class Bulk2Client {

    private static final String API_VERSION = "v41.0";

    @Getter
    private final OkHttpClient client;

    private final String instanceUrl;

    public CreateJobRequest.Builder createJob(String object, OperationEnum operation) {
        return new CreateJobRequest.Builder(this, buildUrl("/services/data/vXX.X/jobs/ingest"),
                object, operation);
    }

    private String buildUrl(String path) {
        boolean hasTrailingSlash = instanceUrl.endsWith("/");

        return instanceUrl + (hasTrailingSlash ? "/" : "") + path.replace("vXX.X", API_VERSION);
    }
}
