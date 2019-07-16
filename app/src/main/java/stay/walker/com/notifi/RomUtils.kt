package stay.walker.com.notifi

import android.os.Build
import android.text.TextUtils
import android.util.Log

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * Created by Administrator on 2018/7/28.
 */

class RomUtils {
    companion object {
        private val TAG = "RomUtils"

        val emuiVersion: Double
            get() {
                try {
                    val e = getSystemProperty("ro.build.version.emui")
                    val version = e!!.substring(e.indexOf("_") + 1)
                    return java.lang.Double.parseDouble(version)
                } catch (var2: Exception) {
                    var2.printStackTrace()
                    return 4.0
                }

            }

        val miuiVersion: Int
            get() {
                val version = getSystemProperty("ro.miui.ui.version.name")
                if (version != null) {
                    try {
                        return Integer.parseInt(version.substring(1))
                    } catch (var2: Exception) {
                        Log.e("RomUtils", "get miui version code error, version : $version")
                    }

                }

                return -1
            }

        fun getSystemProperty(propName: String): String? {
            var input: BufferedReader? = null

            val var4: Any?
            try {
                val ex = Runtime.getRuntime().exec("getprop $propName")
                input = BufferedReader(InputStreamReader(ex.inputStream), 1024)
                val line = input.readLine()
                input.close()
                return line
            } catch (var14: IOException) {
                Log.e("RomUtils", "Unable to read sysprop $propName", var14)
                var4 = null
            } finally {
                if (input != null) {
                    try {
                        input.close()
                    } catch (var13: IOException) {
                        Log.e("RomUtils", "Exception while closing InputStream", var13)
                    }

                }

            }

            return var4 as String?
        }

        fun checkIsHuaweiRom(): Boolean {
            return Build.MANUFACTURER.contains("HUAWEI")
        }

        fun checkIsMiuiRom(): Boolean {
            return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"))
        }

        fun checkIsMeizuRom(): Boolean {
            val meizuFlymeOSFlag = getSystemProperty("ro.build.display.id")
            return if (TextUtils.isEmpty(meizuFlymeOSFlag)) false else meizuFlymeOSFlag!!.contains("flyme") || meizuFlymeOSFlag.toLowerCase().contains("flyme")
        }

        fun checkIs360Rom(): Boolean {
            return Build.MANUFACTURER.contains("QiKU") || Build.MANUFACTURER.contains("360")
        }
    }
}
