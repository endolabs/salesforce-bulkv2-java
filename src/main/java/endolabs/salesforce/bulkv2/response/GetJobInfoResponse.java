package endolabs.salesforce.bulkv2.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GetJobInfoResponse extends JobInfo {

    private Long apexProcessingTime;

    private Long apiActiveProcessingTime;

    private Integer retries;

    private Long totalProcessingTime;

    private Integer numberRecordsProcessed;

    private Integer numberRecordsFailed;
}
