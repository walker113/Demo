package stay.walker.com.notifi;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/7/28.
 */

public class MiuiUtils {
    private static final String TAG = "MiuiUtils";

    public MiuiUtils() {
    }

    public static int getMiuiVersion() {
        String version = RomUtils.Companion.getSystemProperty("ro.miui.ui.version.name");
        if(version != null) {
            try {
                return Integer.parseInt(version.substring(1));
            } catch (Exception var2) {
                Log.e("MiuiUtils", "get miui version code error, version : " + version);
                Log.e("MiuiUtils", Log.getStackTraceString(var2));
            }
        }

        return -1;
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
                Log.e("MiuiUtils", Log.getStackTraceString(var6));
            }
        } else {
            Log.e("MiuiUtils", "Below API 19 cannot invoke!");
        }

        return false;
    }

    public static void applyMiuiPermission(Context context) {
        int versionCode = getMiuiVersion();
        if(versionCode == 5) {
            goToMiuiPermissionActivity_V5(context);
        } else if(versionCode == 6) {
            goToMiuiPermissionActivity_V6(context);
        } else if(versionCode == 7) {
            goToMiuiPermissionActivity_V7(context);
        } else if(versionCode == 8) {
            goToMiuiPermissionActivity_V8(context);
        } else {
            Log.e("MiuiUtils", "this is a special MIUI rom version, its version code " + versionCode);
        }

    }

    private static boolean isIntentAvailable(Intent intent, Context context) {
        return intent == null?false:context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static void goToMiuiPermissionActivity_V5(Context context) {
        Intent intent = null;
        String packageName = context.getPackageName();
        intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        Uri uri = Uri.fromParts("package", packageName, (String)null);
        intent.setData(uri);
        if(isIntentAvailable(intent, context)) {
            WindowUtil.scanForActivity(context).startActivityForResult(intent, 1);
        } else {
            Log.e("MiuiUtils", "intent is not available!");
        }

    }

    public static void goToMiuiPermissionActivity_V6(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if(isIntentAvailable(intent, context)) {
            WindowUtil.scanForActivity(context).startActivityForResult(intent, 1);
        } else {
            Log.e("MiuiUtils", "Intent is not available!");
        }

    }

    public static void goToMiuiPermissionActivity_V7(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if(isIntentAvailable(intent, context)) {
            WindowUtil.scanForActivity(context).startActivityForResult(intent, 1);
        } else {
            Log.e("MiuiUtils", "Intent is not available!");
        }

    }

    public static void goToMiuiPermissionActivity_V8(Context context) {
        Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
        intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
        intent.putExtra("extra_pkgname", context.getPackageName());
        if(isIntentAvailable(intent, context)) {
            context.startActivity(intent);
        } else {
            intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            intent.setPackage("com.miui.securitycenter");
            intent.putExtra("extra_pkgname", context.getPackageName());
            if(isIntentAvailable(intent, context)) {
                WindowUtil.scanForActivity(context).startActivityForResult(intent, 1);
            } else {
                Log.e("MiuiUtils", "Intent is not available!");
            }
        }

    }
}
