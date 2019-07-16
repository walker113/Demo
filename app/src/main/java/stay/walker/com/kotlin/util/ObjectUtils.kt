package stay.walker.com.kotlin.util

import android.annotation.SuppressLint
import stay.walker.com.notifi.AndroidUtils

/**
 * 当一个类用object修饰，类当中的所有函数和成员属性都会变成静态的
 */
@SuppressLint("StaticFieldLeak")
object ObjectUtils {
    val context = AndroidUtils.getApp().applicationContext
    fun save(key: String?, value: String?) {

    }
}