package stay.walker.com.video;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.socks.library.KLog;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import stay.walker.com.retrofitdemo.R;

public class VideoActivity extends Activity {


    private static int RESULT_LOAD_VIDEO = 1;
    String decodableString;

    private static final int REQUEST_VIDEO_CAPTURE = 300;
    private static final int READ_REQUEST_CODE = 200;
    private Uri uri;
    private String pathToStoredVideo;
    private VideoView displayRecordedVideo;
    private static final String SERVER_PATH = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /*
     * PICK THE VIDEO AND EXTRACT ITS ADDRESS
     */
    public void loadVideoFromGallery(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When a video is picked
            if (requestCode == RESULT_LOAD_VIDEO && resultCode == RESULT_OK
                    && null != data) {
                // Get the video from data
                Uri selectedVideo = data.getData();
                String[] filePathColumn = {MediaStore.Video.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedVideo,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                decodableString = cursor.getString(columnIndex);
                cursor.close();
                File file = new File(decodableString);
                Log.i("mok", "done");
                upload(file);
            } else {
                Toast.makeText(this, "You haven't picked any video",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }

    /*
     * UPLOAD THE SELECTED VIDEO TO THE SRVER
     */

    public void upload(File file) {
        final String BASE_URL = "http://172.20.14.16:8888/";
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//添加拦截器到OkHttp，这是最关键的
        httpClient.addInterceptor(logging);
        Retrofit retrofit =
                new Retrofit.Builder()
                .baseUrl(BASE_URL)
                        .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UploadApiService service = retrofit.create(UploadApiService.class);
        MediaType MEDIA_TYPE = MediaType.parse("video/mp4");
        Log.i("mok", MEDIA_TYPE.toString());
        RequestBody requestBody = RequestBody.create(MEDIA_TYPE, file);
        Call<ResponseBody> call =
                service.uploadVideo("desc", requestBody,
                "api_union_120003",
                "02",
                "PAYPCID1000000012",
                "GZ001100627",
                "ebf05b6dd195cd4556636333dc3a27222da0f2b5e5f69d4f4fbada105fc1c3ea",
                ".mp4",
                "OSSOTID1000724"

                );
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                KLog.e(response.code());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                KLog.e(t);
            }

        });
    }
}
