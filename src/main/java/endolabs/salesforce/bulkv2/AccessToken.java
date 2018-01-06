package endolabs.salesforce.bulkv2;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AccessToken {

    private String accessToken;

    private String tokenType;

    private String instanceUrl;

    private String id;

    private String signature;

    private String issuedAt;
}
