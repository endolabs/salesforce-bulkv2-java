# Java client library for Salesforce Bulk API 2.0

A simple java wrapper for Salesforce Bulk API 2.0

## Requirements

Java 8 or later.

## Usage

```java
// create client
Bulk2Client client = new Bulk2ClientBuilder()
        .withPassword("<consumer key>", "<consumer secret>", "<username>", "<password>"
        .build();

CreateJobResponse response = client.createJob("Contact", OperationEnum.INSERT)
        .withContentType("CSV")
        .execute();
```

## Build

```
$ ./gradlew build
```