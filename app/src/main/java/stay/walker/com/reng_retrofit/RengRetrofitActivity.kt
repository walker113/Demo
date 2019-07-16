package stay.walker.com.reng_retrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.gson.Gson
import com.socks.library.KLog
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import stay.walker.com.retrofitdemo.R
import stay.walker.com.retrofitdemo.Repo

class RengRetrofitActivity : AppCompatActivity() {

    val gson = Gson()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_coupons)

        val log = HttpLoggingInterceptor()
        log.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
                .addInterceptor(log)
                .build()


        val retrofit =
                Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

        val api = retrofit.create(GitHubService::class.java)
//        api.getRepos().enqueue(object : Callback<ResponseBody> {
//            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
//                Log.e("TAG", "error")
//            }
//
//            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
//                Log.e("TAG", response.code().toString())
//
//
//            }
//
//        })

        api.getRepos().enqueue(object : Callback<List<GitRepo>> {
            override fun onFailure(call: Call<List<GitRepo>>, t: Throwable) {


            }

            override fun onResponse(call: Call<List<GitRepo>>, response: Response<List<GitRepo>>) {
                KLog.e(response.code())
                Log.e("TAG", gson.toJson(response.body()))
            }

        })

    }



}