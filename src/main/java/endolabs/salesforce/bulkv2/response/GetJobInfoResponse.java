package endolabs.salesforce.bulkv2.response;

public class GetJobInfoResponse extends JobInfo {

    private Long apexProcessingTime;

    private Long apiActiveProcessingTime;

    private Integer retries;

    private Long totalProcessingTime;

    private Integer numberRecordsProcessed;

    private Integer numberRecordsFailed;

    public Long getApexProcessingTime() {
        return apexProcessingTime;
    }

    public void setApexProcessingTime(Long apexProcessingTime) {
        this.apexProcessingTime = apexProcessingTime;
    }

    public Long getApiActiveProcessingTime() {
        return apiActiveProcessingTime;
    }

    public void setApiActiveProcessingTime(Long apiActiveProcessingTime) {
        this.apiActiveProcessingTime = apiActiveProcessingTime;
    }

    public Integer getRetries() {
        return retries;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public Long getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public void setTotalProcessingTime(Long totalProcessingTime) {
        this.totalProcessingTime = totalProcessingTime;
    }

    public Integer getNumberRecordsProcessed() {
        return numberRecordsProcessed;
    }

    public void setNumberRecordsProcessed(Integer numberRecordsProcessed) {
        this.numberRecordsProcessed = numberRecordsProcessed;
    }

    public Integer getNumberRecordsFailed() {
        return numberRecordsFailed;
    }

    public void setNumberRecordsFailed(Integer numberRecordsFailed) {
        this.numberRecordsFailed = numberRecordsFailed;
    }
}
