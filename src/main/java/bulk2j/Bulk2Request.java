package bulk2j;

import bulk2j.request.CreateJobRequest;
import bulk2j.response.CreateJobResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Bulk2Request {

    public CreateJobResponse createJob(Bulk2Client client, CreateJobRequest request) {
        return client.post("/services/data/vXX.X/jobs/ingest", request, CreateJobResponse.class);
    }

    public void uploadJobData(Bulk2Client client) {
        // TODO: not yet implemented
    }

    public void closeOrAbortJob(Bulk2Client client) {
        // TODO: not yet implemented
    }

    public void deleteJob(Bulk2Client client) {
        // TODO: not yet implemented
    }

    public void getAllJobs(Bulk2Client client) {
        // TODO: not yet implemented
    }

    public void getJobInfo(Bulk2Client client) {
        // TODO: not yet implemented
    }

    public void getJobSuccessfulRecordResults(Bulk2Client client) {
        // TODO: not yet implemented
    }

    public void getJobFailedRecordResults(Bulk2Client client) {
        // TODO: not yet implemented
    }

    public void getJobUnprocessedRecordResults(Bulk2Client client) {
        // TODO: not yet implemented
    }
}
