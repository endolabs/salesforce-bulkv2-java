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
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Objects;

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
        return request(url, "GET", null, responseClass);
    }

    public <T> T post(String url, Object requestData, Class<T> responseClass) {
        return request(url, "POST", requestData, responseClass);
    }

    public <T> T put(String url, Object requestData, Class<T> responseClass) {
        return request(url, "PUT", requestData, responseClass);
    }

    public <T> T delete(String url, Object requestData, Class<T> responseClass) {
        return request(url, "DELETE", requestData, responseClass);
    }

    public <T> T patch(String url, Object requestData, Class<T> responseClass) {
        return request(url, "PATCH", requestData, responseClass);
    }

    private <T> T request(String url, String httpMethod, Object requestData, Class<T> responseClass) {
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse(url));

        RequestBody requestBody = (requestData == null) ? null : RequestBody.create(JSON_MEDIA_TYPE, Json.encode(requestData));

        Request request = new Request.Builder()
                .url(httpUrl)
                .method(httpMethod, requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            if (responseBody == null) {
                throw new RuntimeException("response body is null.");
            }

            String responseJson = responseBody.string();
            log.info("{} response {} {}", httpMethod, response.code(), responseJson);

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
}
