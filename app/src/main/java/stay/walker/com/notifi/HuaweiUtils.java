package stay.walker.com.notifi;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/7/28.
 */

public class HuaweiUtils {
    private static final String TAG = "HuaweiUtils";

    public HuaweiUtils() {
    }

    public static boolean checkFloatWindowPermission(Context context) {
        int version = Build.VERSION.SDK_INT;
        return version >= 19?checkOp(context, 24):true;
    }

    public static void applyPermission(Context context) {
        Intent intent;
        ComponentName comp;
        try {
            Intent e = new Intent();
            ComponentName intent1 = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.addviewmonitor.AddViewMonitorActivity");
            e.setComponent(intent1);
            if(RomUtils.getEmuiVersion() == 3.1D) {
                WindowUtil.scanForActivity(context).startActivityForResult(e, 1);
            } else {
                intent1 = new ComponentName("com.huawei.systemmanager", "com.huawei.notificationmanager.ui.NotificationManagmentActivity");
                e.setComponent(intent1);
                WindowUtil.scanForActivity(context).startActivityForResult(e, 1);
            }
        } catch (SecurityException var4) {
            intent = new Intent();
            comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");
            intent.setComponent(comp);
            WindowUtil.scanForActivity(context).startActivityForResult(intent, 1);
            Log.e("HuaweiUtils", Log.getStackTraceString(var4));
        } catch (ActivityNotFoundException var5) {
            intent = new Intent();
            comp = new ComponentName("com.Android.settings", "com.android.settings.permission.TabItem");
            intent.setComponent(comp);
            WindowUtil.scanForActivity(context).startActivityForResult(intent, 1);
            var5.printStackTrace();
            Log.e("HuaweiUtils", Log.getStackTraceString(var5));
        } catch (Exception var6) {
            Toast.makeText(context, "进入设置页面失败，请手动设置", Toast.LENGTH_LONG).show();
            Log.e("HuaweiUtils", Log.getStackTraceString(var6));
        }

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
                Log.e("HuaweiUtils", Log.getStackTraceString(var6));
            }
        } else {
            Log.e("HuaweiUtils", "Below API 19 cannot invoke!");
        }

        return false;
    }
}

