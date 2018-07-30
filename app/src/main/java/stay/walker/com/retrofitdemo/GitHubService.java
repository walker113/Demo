package stay.walker.com.retrofitdemo;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<ResponseBody> listRepos(@Path("user") String user);


    // https://api.github.com/users/walker113/repos

/**
    {
        "accountNo": "0000180011044000",
        "bsnCode": "000402",
        "customerId": "PAYPCID1000003495",
        "orderChannel": "1",
        "pageFlag": "0",
        "rechargeFlag": "0",
        "transferStt": "99",
        "channelFlag": "02",
        "shopNo": "GZ440100239",
        "sign": "f3c946a50c11601afa4aded7194ae220e8bd3570b537b6dad397d6825070461f",
        "terminalNo": "OSSOTID1000296",
        "tranCode": "pay_action_000007"
    }

*/

    @FormUrlEncoded
    @POST("/api/service")
    Call<ResponseBody> login(
            @Field("accountNo") String accountNo,
            @Field("bsnCode") String bsnCode,
            @Field("customerId") String customerId,
            @Field("orderChannel") String orderChannel,
            @Field("pageFlag")  String pageFlag,
            @Field("rechargeFlag") String rechargeFlag,
            @Field("transferStt") String transferStt,
            @Field("channelFlag")  String channelFlag,
            @Field("shopNo")  String shopNo,
            @Field("sign")  String sign,
            @Field("terminalNo")  String terminalNo,
            @Field("tranCode") String tranCode,
            @Field("key")  String key
    );


    @POST("/api/service")
    Call<ResponseBody> getResult(@Body RequestBody requestBody);

}
