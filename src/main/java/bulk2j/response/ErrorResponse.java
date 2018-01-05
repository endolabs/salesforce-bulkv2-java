package bulk2j.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private String errorCode;

    private String message;
}
