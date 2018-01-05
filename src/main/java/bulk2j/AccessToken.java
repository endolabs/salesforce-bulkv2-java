package bulk2j;

import lombok.Data;

@Data
public class AccessToken {

    private String accessToken;

    private String tokenType;

    private String instanceUrl;

    private String id;

    private String signature;

    private String issuedAt;
}
