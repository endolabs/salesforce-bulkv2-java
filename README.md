# Java client library for Salesforce Bulk API 2.0

A simple java wrapper for Salesforce Bulk API 2.0

## Requirements

Java 8 or later.

## Usage

### Create client

```java
Bulk2Client client = new Bulk2ClientBuilder()
        .withPassword("<consumer key>", "<consumer secret>", "<username>", "<password>"
        .build();
```

### Upload CSV data using a separate request

```java
CreateJobResponse createJobResponse = client.createJob("Account", OperationEnum.INSERT)
        .withContentType("CSV")
        .execute();
String jobId = createJobResponse.getId();

String csv = "Name,Description,NumberOfEmployees\n" +
        "TestAccount1,Description of TestAccount1,30\n" +
        "TestAccount2,Another description,40\n" +
        "TestAccount3,Yet another description,50";
client.uploadJobData(jobId, csv)
        .execute();

CloseOrAbortJobResponse closeJobResponse = client.closeOrAbortJob(jobId, JobStateEnum.UPLOAD_COMPLETE)
        .execute();

while (true) {
    TimeUnit.SECONDS.sleep(1);

    GetJobInfoResponse jobInfo = client.getJobInfo(jobId).execute();
    if (jobInfo.isFinished()) {
        break;
    }
}
```

## Build

```
$ ./gradlew build
```