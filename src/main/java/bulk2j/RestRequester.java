package bulk2j;

import bulk2j.response.ErrorResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

@Slf4j
public class RestRequester {

    private static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    private static final TypeReference<List<ErrorResponse>> ERRORS_TYPE_REFERENCE = new TypeReference<List<ErrorResponse>>() {

    };

    private final OkHttpClient client;

    public RestRequester(OkHttpClient client) {
        this.client = client;
    }

    public <T> T get(String url, Class<T> responseClass) {
        return null;
    }

    public <T> T post(String url, Object requestData, Class<T> responseClass) {
        HttpUrl httpUrl = HttpUrl.parse(url);

        String requestJson = Json.encode(requestData);
        log.info("request json {}", requestJson);
        RequestBody requestBody = RequestBody.create(JSON_MEDIA_TYPE, requestJson);

        Request request = new Request.Builder()
                .url(httpUrl)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseJson = response.body().string();
            log.info("post response {} {}", response.code(), responseJson);

            if (response.isSuccessful()) {
                return Json.decode(responseJson, responseClass);
            } else {
                List<ErrorResponse> errors = Json.decode(responseJson, ERRORS_TYPE_REFERENCE);
                log.error("error : {}", errors);
                throw new RuntimeException("error.");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public <T> T put(String url, Object requestData, Class<T> responseClass) {
        return null;
    }

    public <T> T delete(String url, Object requestData, Class<T> responseClass) {
        return null;
    }

    public <T> T patch(String url, Object requestData, Class<T> responseClass) {
        return null;
    }
}
