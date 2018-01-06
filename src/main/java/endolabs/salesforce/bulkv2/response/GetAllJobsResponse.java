package endolabs.salesforce.bulkv2.response;

import java.util.List;

public class GetAllJobsResponse {

    private Boolean done;

    private List<JobInfo> records;

    private String nextRecordsUrl;

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public List<JobInfo> getRecords() {
        return records;
    }

    public void setRecords(List<JobInfo> records) {
        this.records = records;
    }

    public String getNextRecordsUrl() {
        return nextRecordsUrl;
    }

    public void setNextRecordsUrl(String nextRecordsUrl) {
        this.nextRecordsUrl = nextRecordsUrl;
    }
}
