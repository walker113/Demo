package stay.walker.com.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.socks.library.KLog;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Arrays;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);

        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://api.github.com/")
                .baseUrl("https://api.pcidata.cn:30018/")
                .build();

        service = retrofit.create(GitHubService.class);



        new Thread(new Runnable() {
            @Override
            public void run() {
//                request();
                doTest();
            }
        }).start();
    }

    GitHubService service;

    private void request() {


//        Call<ResponseBody> repos = service.listRepos("walker113");
//        try {
//            repos.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        Call<ResponseBody> login = service.login(
                "0000200011326036",
                "000402",
                "PAYPCID1000000807",
                "1",
                "0",
                "0",
                "99",
                "02",
                "PDS007100240",
                "9224fc8d6f63822286db43008de390bb0e4df312913a2afd0b071bd0a03f8644",
                "OSSOTID1000400",
                "pay_action_000007",
                "ip6dbq1y00873m1h5n7a6xf4oa510g2r"
        );

        try {
            login.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void doTest() {
        JSONObject requestData = new JSONObject();
        try {

            requestData.put("accountNo", "0000200011326036");
            requestData.put("bsnCode", "000402");
            requestData.put("customerId", "PAYPCID1000000807");
            requestData.put("orderChannel", "1");
            requestData.put("pageFlag", "0");
            requestData.put("rechargeFlag", "0");
            requestData.put("transferStt", "99");
            requestData.put("channelFlag", "02");
            requestData.put("shopNo", "PDS007100240");
            requestData.put("sign", "9224fc8d6f63822286db43008de390bb0e4df312913a2afd0b071bd0a03f8644");
            requestData.put("terminalNo", "OSSOTID1000400");
            requestData.put("tranCode", "pay_action_000007");
            requestData.put("key", "ip6dbq1y00873m1h5n7a6xf4oa510g2r");
        } catch (JSONException e) {
            e.printStackTrace();
        }




        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),requestData.toString());


        try {
            Call<ResponseBody> result = service.getResult(requestBody);
            result.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class CLoudQRStatusReq {

        public String tranCode, customerId, orderChannel, accountNo, transferStt, bsnCode, rechargeFlag, pageFlag;


        public String getParamSign()  {
            String sign = "";
            StringBuilder params = new StringBuilder();
            Field[] fs = getClass().getFields();
            String []fieldNames = new String[fs.length];
            try {
                for (int i = 0; i < fs.length; i++) {
                    fieldNames[i] = fs[i].getName();
                }
                Arrays.sort(fieldNames);
                for (int i = 0; i < fs.length; i++) {
                    String s = (String)getClass().getField(fieldNames[i]).get(this);
                    if (!TextUtils.isEmpty(s) && !fieldNames[i].equals("imageBlob")) {

                        if (params.length() == 0) {
                            params.append(fieldNames[i] + "=" + URLEncoder.encode(s));
                        } else {
                            params.append("&" + fieldNames[i] + "=" + URLEncoder.encode(s));
                        }
                    }

                }
                params.append("&key=" + key);
                KLog.w("sign = " + params.toString());
                sign = SHATest.encrypt(params.toString());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sign;
        }
    }


    public static String key = "ip6dbq1y00873m1h5n7a6xf4oa510g2r";



}
