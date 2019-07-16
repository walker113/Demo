package stay.walker.com.reng_retrofit

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import stay.walker.com.retrofitdemo.Repo

interface GitHubService {

    @GET("users/rengwuxian/repos")
    fun getRepos(): Call<List<GitRepo>>
//    fun getRepos(): Call<ResponseBody>

}