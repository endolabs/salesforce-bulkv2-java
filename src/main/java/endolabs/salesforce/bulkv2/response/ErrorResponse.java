package endolabs.salesforce.bulkv2.response;

public class ErrorResponse {

    private String errorCode;

    private String message;

    @Override
    public String toString() {
        return "{ errorCode: " + errorCode + ", message: " + message + " }";
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
