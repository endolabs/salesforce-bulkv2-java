package endolabs.salesforce.bulkv2;

import endolabs.salesforce.bulkv2.request.CloseOrAbortJobRequest;
import endolabs.salesforce.bulkv2.request.CreateJobRequest;
import endolabs.salesforce.bulkv2.request.GetAllJobsRequest;
import endolabs.salesforce.bulkv2.response.CloseOrAbortJobResponse;
import endolabs.salesforce.bulkv2.response.CreateJobResponse;
import endolabs.salesforce.bulkv2.response.GetAllJobsResponse;
import endolabs.salesforce.bulkv2.response.GetJobInfoResponse;
import endolabs.salesforce.bulkv2.response.JobInfo;
import endolabs.salesforce.bulkv2.type.JobStateEnum;
import endolabs.salesforce.bulkv2.type.OperationEnum;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

import java.io.Reader;
import java.util.function.Consumer;

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

    public CreateJobResponse createJob(String object, OperationEnum operation, Consumer<CreateJobRequest.Builder> requestBuilder) {
        String url = buildUrl("/services/data/vXX.X/jobs/ingest");

        CreateJobRequest.Builder builder = new CreateJobRequest.Builder(object, operation);
        requestBuilder.accept(builder);

        return requester.post(url, builder.build(), CreateJobResponse.class);
    }

    public CloseOrAbortJobResponse closeOrAbortJob(String jobId, JobStateEnum state) {
        String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId);

        CloseOrAbortJobRequest.Builder builder = new CloseOrAbortJobRequest.Builder(state);

        return requester.patch(url, builder.build(), CloseOrAbortJobResponse.class);
    }

    public void uploadJobData(String jobId, String csvContent) {
        String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId + "/batches");

        requester.putCsv(url, csvContent, Void.class);
    }

    public void deleteJob(String jobId) {
        String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId);

        requester.delete(url, null, Void.class);
    }

    public GetAllJobsResponse getAllJobs(Consumer<GetAllJobsRequest.Builder> requestBuilder) {
        String url = buildUrl("/services/data/vXX.X/jobs/ingest");

        GetAllJobsRequest.Builder builder = new GetAllJobsRequest.Builder();
        requestBuilder.accept(builder);

        return requester.get(url, builder.buildParameters(), GetAllJobsResponse.class);
    }

    public GetJobInfoResponse getJobInfo(String jobId) {
        String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId);

        return requester.get(url, GetJobInfoResponse.class);
    }

    public Reader getJobSuccessfulRecordResults(String jobId) {
        String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId + "/successfulResults/");

        return requester.getCsv(url);
    }

    public Reader getJobFailedRecordResults(String jobId) {
        String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId + "/failedResults/");

        return requester.getCsv(url);
    }

    public Reader getJobUnprocessedRecordResults(String jobId) {
        String url = buildUrl("/services/data/vXX.X/jobs/ingest/" + jobId + "/unprocessedrecords/");

        return requester.getCsv(url);
    }

    // alias

    public JobInfo closeJob(String jobId) {
        return closeOrAbortJob(jobId, JobStateEnum.UPLOAD_COMPLETE);
    }

    public JobInfo abortJob(String jobId) {
        return closeOrAbortJob(jobId, JobStateEnum.ABORTED);
    }

    private String buildUrl(String path) {
        boolean hasTrailingSlash = instanceUrl.endsWith("/");

        return instanceUrl + (hasTrailingSlash ? "/" : "") + path.replace("vXX.X", API_VERSION);
    }
}
