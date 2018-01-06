package endolabs.salesforce.bulkv2.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * State of processing for the job.
 */
public enum JobStateEnum {

    /**
     * The job has been created, and data can be added to the job.
     */
    OPEN("Open"),

    /**
     * No new data can be added to this job. You can’t edit or save a closed job.
     */
    UPLOAD_COMPLETE("UploadComplete"),

    /**
     * The job is being processed by Salesforce. This includes automatic optimized chunking of job data and processing of job operations.
     */
    IN_PROGRESS("InProgress"),

    /**
     * The job has been aborted. You can abort a job if you created it or if you have the “Manage Data Integrations” permission.
     */
    ABORTED("Aborted"),

    /**
     * The job was processed by Salesforce.
     */
    JOB_COMPLETE("JobComplete"),

    /**
     * The job has failed. Job data that was successfully processed isn’t rolled back.
     */
    FAILED("Failed");

    private static EnumSet<JobStateEnum> FINISHED_STATUS = EnumSet.of(JOB_COMPLETE, FAILED, ABORTED);

    private final String value;

    JobStateEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String toJsonValue() {
        return value;
    }

    @JsonCreator
    public static JobStateEnum fromValue(String value) {
        return Arrays.stream(values())
                .filter(v -> v.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.valueOf(value)));
    }

    public boolean isFinished() {
        return FINISHED_STATUS.contains(this);
    }
}
