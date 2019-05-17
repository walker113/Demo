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

public class MeizuUtils {
    private static final String TAG = "MeizuUtils";

    public MeizuUtils() {
    }

    public static boolean checkFloatWindowPermission(Context context) {
        int version = Build.VERSION.SDK_INT;
        return version >= 19?checkOp(context, 24):true;
    }

    public static void applyPermission(Context context) {
        Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
        intent.setClassName("com.meizu.safe", "com.meizu.safe.security.AppSecActivity");
        intent.putExtra("packageName", context.getPackageName());
        WindowUtil.scanForActivity(context).startActivityForResult(intent, 1);
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
                Log.e("MeizuUtils", Log.getStackTraceString(var6));
            }
        } else {
            Log.e("MeizuUtils", "Below API 19 cannot invoke!");
        }

        return false;
    }
}

