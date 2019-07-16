package stay.walker.com.reng_retrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import com.socks.library.KLog
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import stay.walker.com.retrofitdemo.R
import stay.walker.com.retrofitdemo.Repo
import java.io.IOException

class RengOkHttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_coupons)


        val hostname = "api.github.com";
        val certificatePinner = CertificatePinner.Builder()
//            .add(hostname, "sha256/AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=")
            .add(hostname, "sha256/y2HhTRXXLdmAF1esYBb/muQUl3BIBdmEB8jUvMrGc28=",
                    "sha256/k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws=",
                    "sha256/WoiWRyIOVNa9ihaBciRSC7XHjliYS9VwUGOIud4PB18=")
            .build()

        val client = OkHttpClient.Builder().certificatePinner(certificatePinner).build()
        client.newCall(Request.Builder()
                .url("https://api.github.com/")
                .build()
        ).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.e("TAG", "error")
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {

                Log.e("TAG", response.code().toString())
            }

        })


    }


}