package stay.walker.com.notifi;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/7/28.
 */

public class QikuUtils {
    private static final String TAG = "QikuUtils";

    public QikuUtils() {
    }

    public static boolean checkFloatWindowPermission(Context context) {
        int version = Build.VERSION.SDK_INT;
        return version >= 19?checkOp(context, 24):true;
    }

    @TargetApi(19)
    private static boolean checkOp(Context context, int op) {
        int version = Build.VERSION.SDK_INT;
        if(version >= 19) {
            AppOpsManager manager = (AppOpsManager)context.getSystemService(Context.APP_OPS_SERVICE);

            try {
                Class e = AppOpsManager.class;
                Method method = e.getDeclaredMethod("checkOp", new Class[]{Integer.TYPE, Integer.TYPE, String.class});
                return 0 == ((Integer)method.invoke(manager, new Object[]{Integer.valueOf(op), Integer.valueOf(Binder.getCallingUid()), context.getPackageName()})).intValue();
            } catch (Exception var6) {
                Log.e("QikuUtils", Log.getStackTraceString(var6));
            }
        } else {
            Log.e("", "Below API 19 cannot invoke!");
        }

        return false;
    }

    public static void applyPermission(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.Settings$OverlaySettingsActivity");
        if(isIntentAvailable(intent, context)) {
            context.startActivity(intent);
        } else {
            intent.setClassName("com.qihoo360.mobilesafe", "com.qihoo360.mobilesafe.ui.index.AppEnterActivity");
            if(isIntentAvailable(intent, context)) {
                WindowUtil.scanForActivity(context).startActivityForResult(intent, 1);
            } else {
                Log.e("QikuUtils", "can\'t open permission page with particular name, please use \"adb shell dumpsys activity\" command and tell me the name of the float window permission page");
            }
        }

    }

    private static boolean isIntentAvailable(Intent intent, Context context) {
        return intent == null?false:context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }
}
