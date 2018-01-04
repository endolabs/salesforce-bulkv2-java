package demo;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

@Slf4j
public class Main {

    public static void main(String... args) throws Exception {

    }

    private void doHttpGet(OkHttpClient client, String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();

        String body = (responseBody == null) ? "" : responseBody.string();
        log.info("response body = {}", body);
    }
}
