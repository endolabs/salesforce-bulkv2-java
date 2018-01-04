package demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;

@Slf4j
@RequiredArgsConstructor
public class Bulk2Client {

    private final OkHttpClient client;

    private final String instanceUrl;

    public <T> T get(String path, Class<T> responseClass) {
        return null;
    }

    public <T> T post(String path, Object requestData, Class<T> responseClass) {
        return null;
    }

    public <T> T put(String path, Object requestData, Class<T> responseClass) {
        return null;
    }

    public <T> T delete(String path, Object requestData, Class<T> responseClass) {
        return null;
    }

    public <T> T patch(String path, Object requestData, Class<T> responseClass) {
        return null;
    }
}
