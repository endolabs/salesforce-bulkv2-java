package bulk2j;

import bulk2j.request.CloseOrAbortJobRequest;
import bulk2j.request.CreateJobRequest;
import bulk2j.request.GetAllJobsRequest;
import bulk2j.response.GetJobInfoResponse;
import bulk2j.type.JobStateEnum;
import bulk2j.type.OperationEnum;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

import java.io.Reader;

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

    public CloseOrAbortJobRequest.Builder closeOrAbortJob(String jobId, JobStateEnum state) {
        return new CloseOrAbortJobRequest.Builder(requester, buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId),
                state);
    }

    public Executable<Void> uploadJobData(String jobId, String csvContent) {
        return () -> {
            String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId + "/batches");
            return requester.putCsv(url, csvContent, Void.class);
        };
    }

    public Executable<Void> deleteJob(String jobId) {
        return () -> {
            String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId);
            return requester.delete(url, null, Void.class);
        };
    }

    public GetAllJobsRequest.Builder getAllJobs() {
        return new GetAllJobsRequest.Builder(requester, buildUrl("/services/data/vXX.X/jobs/ingest"));
    }

    public Executable<GetJobInfoResponse> getJobInfo(String jobId) {
        return () -> {
            String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId);
            return requester.get(url, GetJobInfoResponse.class);
        };
    }

    public Executable<Reader> getJobSuccessfulRecordResults(String jobId) {
        return () -> {
            String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId + "/successfulResults/");
            return requester.getCsv(url);
        };
    }

    public Executable<Reader> getJobFailedRecordResults(String jobId) {
        return () -> {
            String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId + "/failedResults/");
            return requester.getCsv(url);
        };
    }

    public Executable<Reader> getJobUnprocessedRecordResults(String jobId) {
        return () -> {
            String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId + "/unprocessedrecords/");
            return requester.getCsv(url);
        };
    }

    private String buildUrl(String path) {
        boolean hasTrailingSlash = instanceUrl.endsWith("/");

        return instanceUrl + (hasTrailingSlash ? "/" : "") + path.replace("vXX.X", API_VERSION);
    }
}
