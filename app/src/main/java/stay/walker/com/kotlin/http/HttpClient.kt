package stay.walker.com.kotlin.http

import com.google.gson.Gson
import okhttp3.*
import stay.walker.com.kotlin.EntityCallBack
import java.io.IOException
import java.lang.reflect.Type

object HttpClient : OkHttpClient() {

    private val gson = Gson()

    private fun <T> convert(json: String?, type: Type): T {
        return gson.fromJson(json, type)
    }

    fun <T> get(path: String, type: Type, entityCallBack: EntityCallBack<T>) {

        val request =
                Request.Builder().url("https://baidu.com$path").build()

        val call = this.newCall(request)

        call.enqueue(object:Callback{
            override fun onResponse(call: Call, response: Response) {
                when (response.code()) {
                    in 200..299 -> {
                        val json: String? = response.body()!!.string()
                        entityCallBack.onSuccess(convert(json, type) as T)
                    }
                    in 400..499 -> entityCallBack.onFailure("客户端错误")
                    in 500..599 -> entityCallBack.onFailure("服务端错误")
                    else -> entityCallBack.onFailure("未知错误")
                }

            }
            override fun onFailure(call: Call, e: IOException) {
                entityCallBack.onFailure("Exception")
            }
        })


    }

}