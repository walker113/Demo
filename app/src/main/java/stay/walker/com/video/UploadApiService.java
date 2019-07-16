package stay.walker.com.video;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface UploadApiService
{
    @Multipart
    @PUT("api/service")
    Call<ResponseBody> uploadVideo(
            @Part("description") String description,
            @Part(("videos\"; filename=\"a.mp4\" ")) RequestBody video,

            @Part("apiName") String apiName,
            @Part("channelFlag") String channelFlag,
            @Part("customerid") String customerid,
            @Part("merid") String merid,
            @Part("sign")  String sign,
            @Part("sjVideofm") String sjVideofm,
            @Part("terminalno") String terminalno
            );
}