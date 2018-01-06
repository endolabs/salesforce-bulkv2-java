package endolabs.salesforce.bulkv2.response;

import lombok.Data;

import java.util.List;

@Data
public class GetAllJobsResponse {

    private Boolean done;

    private List<JobInfo> records;

    private String nextRecordsUrl;
}
