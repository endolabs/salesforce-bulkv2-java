# Java client library for Salesforce Bulk API 2.0

## Requirements

Java 8 or later.

## Usage

### Create a Job

```java
Bulk2Client client = new Bulk2ClientBuilder()
        .withPassword("<consumer key>", "<consumer secret>", "<username>", "<password>"
        .build();

CreateJobResponse response = client.createJob()
        .withObject("Contact")
        .withOperation(OperationEnum.INSERT)
        .withContentType("CSV")
        .execute();
```

## Build

TBD