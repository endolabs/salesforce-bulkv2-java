package bulk2j;

import bulk2j.request.CreateJobRequest;
import bulk2j.request.GetAllJobsRequest;
import bulk2j.type.OperationEnum;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

@Slf4j
public class Bulk2Client {

    private static final String API_VERSION = "v41.0";

    private final OkHttpClient client;

    private final String instanceUrl;

    private final RestRequester requester;

    public Bulk2Client(OkHttpClient client, String instanceUrl) {
        this.client = client;
        this.instanceUrl = instanceUrl;
        this.requester = new RestRequester(client);
    }

    public CreateJobRequest.Builder createJob(String object, OperationEnum operation) {
        return new CreateJobRequest.Builder(requester, buildUrl("/services/data/vXX.X/jobs/ingest"),
                object, operation);
    }

    public GetAllJobsRequest.Builder getAllJobs() {
        return new GetAllJobsRequest.Builder(requester, buildUrl("/services/data/vXX.X/jobs/ingest"));
    }

    private String buildUrl(String path) {
        boolean hasTrailingSlash = instanceUrl.endsWith("/");

        return instanceUrl + (hasTrailingSlash ? "/" : "") + path.replace("vXX.X", API_VERSION);
    }
}
