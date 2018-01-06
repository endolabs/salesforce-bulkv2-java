package bulk2j;

public class BulkRequestException extends RuntimeException {

    public BulkRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BulkRequestException(String message) {
        super(message);
    }

    public BulkRequestException(Exception cause) {
        super(cause);
    }
}
