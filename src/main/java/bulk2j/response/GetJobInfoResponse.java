package bulk2j.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetJobInfoResponse extends JobInfo {

    private Long apexProcessingTime;

    private Long apiActiveProcessingTime;

    private Integer retries;

    private Long totalProcessingTime;

    private Integer numberRecordsProcessed;

    private Integer numberRecordsFailed;
}
