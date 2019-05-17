package stay.walker.com.notifi;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2018/7/28.
 */

public class RomUtils {
    private static final String TAG = "RomUtils";

    public RomUtils() {
    }

    public static double getEmuiVersion() {
        try {
            String e = getSystemProperty("ro.build.version.emui");
            String version = e.substring(e.indexOf("_") + 1);
            return Double.parseDouble(version);
        } catch (Exception var2) {
            var2.printStackTrace();
            return 4.0D;
        }
    }

    public static int getMiuiVersion() {
        String version = getSystemProperty("ro.miui.ui.version.name");
        if(version != null) {
            try {
                return Integer.parseInt(version.substring(1));
            } catch (Exception var2) {
                Log.e("RomUtils", "get miui version code error, version : " + version);
            }
        }

        return -1;
    }

    public static String getSystemProperty(String propName) {
        BufferedReader input = null;

        Object var4;
        try {
            Process ex = Runtime.getRuntime().exec("getprop " + propName);
            input = new BufferedReader(new InputStreamReader(ex.getInputStream()), 1024);
            String line = input.readLine();
            input.close();
            return line;
        } catch (IOException var14) {
            Log.e("RomUtils", "Unable to read sysprop " + propName, var14);
            var4 = null;
        } finally {
            if(input != null) {
                try {
                    input.close();
                } catch (IOException var13) {
                    Log.e("RomUtils", "Exception while closing InputStream", var13);
                }
            }

        }

        return (String)var4;
    }

    public static boolean checkIsHuaweiRom() {
        return Build.MANUFACTURER.contains("HUAWEI");
    }

    public static boolean checkIsMiuiRom() {
        return !TextUtils.isEmpty(getSystemProperty("ro.miui.ui.version.name"));
    }

    public static boolean checkIsMeizuRom() {
        String meizuFlymeOSFlag = getSystemProperty("ro.build.display.id");
        return TextUtils.isEmpty(meizuFlymeOSFlag)?false:meizuFlymeOSFlag.contains("flyme") || meizuFlymeOSFlag.toLowerCase().contains("flyme");
    }

    public static boolean checkIs360Rom() {
        return Build.MANUFACTURER.contains("QiKU") || Build.MANUFACTURER.contains("360");
    }
}
