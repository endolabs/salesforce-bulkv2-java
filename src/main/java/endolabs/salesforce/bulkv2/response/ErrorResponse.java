package endolabs.salesforce.bulkv2.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private String errorCode;

    private String message;

    @Override
    public String toString() {
        return "{ errorCode: " + errorCode + ", message: " + message + " }";
    }
}
