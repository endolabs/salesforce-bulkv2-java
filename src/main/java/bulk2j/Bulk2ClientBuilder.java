package bulk2j;

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

@Slf4j
public class Bulk2ClientBuilder {

    public Bulk2Client create(String consumerKey, String consumerSecret, String username, String password)
            throws IOException {
        AccessToken token = getAccessToken(consumerKey, consumerSecret, username, password);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(authorizationInterceptor(token.getAccessToken()))
                .addInterceptor(httpLoggingInterceptor(HttpLoggingInterceptor.Level.BODY))
                .build();
        return new Bulk2Client(client, token.getInstanceUrl());
    }

    private AccessToken getAccessToken(String consumerKey, String consumerSecret, String username, String password)
            throws IOException {
        HttpUrl authorizeUrl = HttpUrl.parse("https://login.salesforce.com/services/oauth2/token").newBuilder().build();

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

        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();

        return Json.decode(responseBody.string(), AccessToken.class);
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
