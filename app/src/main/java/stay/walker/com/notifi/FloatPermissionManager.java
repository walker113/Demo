package stay.walker.com.notifi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/7/28.
 */

public class FloatPermissionManager {
    private static final String TAG = "FloatPermissionManager";
    public static final int PERMISSION_REQUEST_CODE = 1;
    private static volatile FloatPermissionManager instance;
    private Dialog dialog;

    public FloatPermissionManager() {
    }

    public static FloatPermissionManager getInstance() {
        if(instance == null) {
            Class var0 = FloatPermissionManager.class;
            synchronized(FloatPermissionManager.class) {
                if(instance == null) {
                    instance = new FloatPermissionManager();
                }
            }
        }

        return instance;
    }

    public boolean checkPermission(Context context) {
        if(Build.VERSION.SDK_INT < 23) {
            if(RomUtils.Companion.checkIsMiuiRom()) {
                return this.miuiPermissionCheck(context);
            }

            if(RomUtils.Companion.checkIsMeizuRom()) {
                return this.meizuPermissionCheck(context);
            }

            if(RomUtils.Companion.checkIsHuaweiRom()) {
                return this.huaweiPermissionCheck(context);
            }

            if(RomUtils.Companion.checkIs360Rom()) {
                return this.qikuPermissionCheck(context);
            }
        }

        return this.commonROMPermissionCheck(context);
    }

    private boolean huaweiPermissionCheck(Context context) {
        return HuaweiUtils.checkFloatWindowPermission(context);
    }

    private boolean miuiPermissionCheck(Context context) {
        return MiuiUtils.checkFloatWindowPermission(context);
    }

    private boolean meizuPermissionCheck(Context context) {
        return MeizuUtils.checkFloatWindowPermission(context);
    }

    private boolean qikuPermissionCheck(Context context) {
        return QikuUtils.checkFloatWindowPermission(context);
    }

    private boolean commonROMPermissionCheck(Context context) {
        if(RomUtils.Companion.checkIsMeizuRom()) {
            return this.meizuPermissionCheck(context);
        } else {
            Boolean result = Boolean.valueOf(true);
            if(Build.VERSION.SDK_INT >= 23) {
                try {
                    Class e = Settings.class;
                    Method canDrawOverlays = e.getDeclaredMethod("canDrawOverlays", new Class[]{Context.class});
                    result = (Boolean)canDrawOverlays.invoke((Object)null, new Object[]{context});
                } catch (Exception var5) {
                    Log.e("FloatPermissionManager", Log.getStackTraceString(var5));
                }
            }

            return result.booleanValue();
        }
    }

    public void applyPermission(Context context) {
        if(Build.VERSION.SDK_INT < 23) {
            if(RomUtils.Companion.checkIsMiuiRom()) {
                this.miuiROMPermissionApply(context);
            } else if(RomUtils.Companion.checkIsMeizuRom()) {
                this.meizuROMPermissionApply(context);
            } else if(RomUtils.Companion.checkIsHuaweiRom()) {
                this.huaweiROMPermissionApply(context);
            } else if(RomUtils.Companion.checkIs360Rom()) {
                this.ROM360PermissionApply(context);
            }
        }

        this.commonROMPermissionApply(context);
    }

    private void ROM360PermissionApply(final Context context) {
        this.showConfirmDialog(context, new FloatPermissionManager.OnConfirmResult() {
            public void confirmResult(boolean confirm) {
                if(confirm) {
                    QikuUtils.applyPermission(context);
                } else {
                    Log.e("FloatPermissionManager", "ROM:360, user manually refuse OVERLAY_PERMISSION");
                }

            }
        });
    }

    private void huaweiROMPermissionApply(final Context context) {
        this.showConfirmDialog(context, new FloatPermissionManager.OnConfirmResult() {
            public void confirmResult(boolean confirm) {
                if(confirm) {
                    HuaweiUtils.applyPermission(context);
                } else {
                    Log.e("FloatPermissionManager", "ROM:huawei, user manually refuse OVERLAY_PERMISSION");
                }

            }
        });
    }

    private void meizuROMPermissionApply(final Context context) {
        this.showConfirmDialog(context, new FloatPermissionManager.OnConfirmResult() {
            public void confirmResult(boolean confirm) {
                if(confirm) {
                    MeizuUtils.applyPermission(context);
                } else {
                    Log.e("FloatPermissionManager", "ROM:meizu, user manually refuse OVERLAY_PERMISSION");
                }

            }
        });
    }

    private void miuiROMPermissionApply(final Context context) {
        this.showConfirmDialog(context, new FloatPermissionManager.OnConfirmResult() {
            @Override
            public void confirmResult(boolean confirm) {
                if(confirm) {
                    MiuiUtils.applyMiuiPermission(context);
                } else {
                    Log.e("FloatPermissionManager", "ROM:miui, user manually refuse OVERLAY_PERMISSION");
                }

            }
        });
    }

    private void commonROMPermissionApply(final Context context) {
        if(RomUtils.Companion.checkIsMeizuRom()) {
            this.meizuROMPermissionApply(context);
        } else if(Build.VERSION.SDK_INT >= 23) {
            this.showConfirmDialog(context, new FloatPermissionManager.OnConfirmResult() {
                public void confirmResult(boolean confirm) {
                    if(confirm) {
                        try {
                            Class e = Settings.class;
                            Field field = e.getDeclaredField("ACTION_MANAGE_OVERLAY_PERMISSION");
                            Intent intent = new Intent(field.get((Object)null).toString());
                            intent.setData(Uri.parse("package:" + context.getPackageName()));
                            WindowUtil.scanForActivity(context).startActivityForResult(intent, 1);
                        } catch (Exception var5) {
                            Log.e("FloatPermissionManager", Log.getStackTraceString(var5));
                        }
                    } else {
                        Log.d("FloatPermissionManager", "user manually refuse OVERLAY_PERMISSION");
                    }

                }
            });
        }

    }

    private void showConfirmDialog(Context context, FloatPermissionManager.OnConfirmResult result) {
        this.showConfirmDialog(context, "您的手机没有授予悬浮窗权限，开启后才能正常使用此功能", result);
    }

    private void showConfirmDialog(Context context, String message, final FloatPermissionManager.OnConfirmResult result) {
        if(this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }

        this.dialog = (new AlertDialog.Builder(context)).setCancelable(true).setTitle("").setMessage(message).setPositiveButton("现在去开启", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result.confirmResult(true);
                dialog.dismiss();
            }
        }).setNegativeButton("暂不开启", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                result.confirmResult(false);
                dialog.dismiss();
            }
        }).create();
        this.dialog.show();
    }

    private interface OnConfirmResult {
        void confirmResult(boolean var1);
    }
}