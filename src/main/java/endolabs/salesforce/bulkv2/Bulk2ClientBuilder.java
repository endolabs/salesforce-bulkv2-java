package endolabs.salesforce.bulkv2;

import lombok.extern.slf4j.Slf4j;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.util.function.Supplier;

@Slf4j
public class Bulk2ClientBuilder {

    private static final String TOKEN_REQUEST_ENDPOINT = "https://login.salesforce.com/services/oauth2/token";

    private static final String TOKEN_REQUEST_ENDPOINT_SANDBOX = "https://test.salesforce.com/services/oauth2/token";

    private boolean useSandbox;

    private Supplier<AccessToken> accessTokenSupplier;

    public Bulk2ClientBuilder withPassword(String consumerKey, String consumerSecret, String username, String password) {
        this.accessTokenSupplier = () -> this.getAccessTokenUsingPassword(consumerKey, consumerSecret, username, password);

        return this;
    }

    public Bulk2ClientBuilder withSessionId(String token, String instanceUrl) {
        this.accessTokenSupplier = () -> {
            AccessToken accessToken = new AccessToken();
            accessToken.setAccessToken(token);
            accessToken.setInstanceUrl(instanceUrl);
            return accessToken;
        };

        return this;
    }

    public Bulk2ClientBuilder useSandbox() {
        this.useSandbox = true;
        return this;
    }

    public Bulk2Client build()
            throws IOException {
        AccessToken token = accessTokenSupplier.get();

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(authorizationInterceptor(token.getAccessToken()))
                .addInterceptor(httpLoggingInterceptor(HttpLoggingInterceptor.Level.BODY))
                .build();
        return new Bulk2Client(client, token.getInstanceUrl());
    }

    private AccessToken getAccessTokenUsingPassword(String consumerKey, String consumerSecret, String username, String password) {
        String endpoint = useSandbox ? TOKEN_REQUEST_ENDPOINT_SANDBOX : TOKEN_REQUEST_ENDPOINT;
        HttpUrl authorizeUrl = HttpUrl.parse(endpoint).newBuilder().build();

        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", "password")
                .add("client_id", consumerKey)
                .add("client_secret", consumerSecret)
                .add("username", username)
                .add("password", password)
                .build();

        Request request = new Request.Builder()
                .url(authorizeUrl)
                .post(requestBody)
                .build();

        OkHttpClient client = new OkHttpClient().newBuilder()
                // .addInterceptor(new SigningInterceptor(consumer))
                .addInterceptor(httpLoggingInterceptor(HttpLoggingInterceptor.Level.BASIC))
                .build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody responseBody = response.body();

            return Json.decode(responseBody.string(), AccessToken.class);
        } catch (IOException e) {
            throw new BulkRequestException(e);
        }
    }

    private Interceptor authorizationInterceptor(String token) {
        return chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(request);
        };
    }

    private HttpLoggingInterceptor httpLoggingInterceptor(HttpLoggingInterceptor.Level level) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> log.info(message));
        logging.setLevel(level);

        return logging;
    }
}
