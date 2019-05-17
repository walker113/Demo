package stay.walker.com.web;

import com.google.gson.Gson;
import com.r.http.cn.RHttp;
import com.socks.library.KLog;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.util.Map;

/**
 * @author 009632
 * RxJava + Retrofit 工具类
 */
public class RNetUtils {
    private static String sJdBaseUrl;
    private static String sJdApiUrl;

    public static void initJdApi(String baseUrl, String apiUrl) {
        sJdBaseUrl = baseUrl;
        sJdApiUrl = apiUrl;
    }

    public static void doJdPost(String json, LifecycleProvider lifecycle, RHttpCallback callback) {
        Gson gson = new Gson();

        Map<String, Object> request = gson.fromJson(json, Map.class);
        KLog.e(request.toString());
        RHttp http = new RHttp.Builder()
                .post()
                .baseUrl(sJdBaseUrl)
                .apiUrl(sJdApiUrl)
                .addParameter(request)
                .lifecycle(lifecycle)
                .build();

        http.request(callback);
    }

    public static void doJdPost(String baseUrl, String host , Map request, LifecycleProvider lifecycle, RHttpCallback callback) {
        RHttp http = new RHttp.Builder()
                .post()
                .baseUrl(baseUrl)
                .apiUrl(host)
                .addParameter(request)
                .lifecycle(lifecycle)
                .build();

        http.request(callback);
    }
}