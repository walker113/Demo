package stay.walker.com.sign;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;

import stay.walker.com.retrofitdemo.BuildConfig;


////#define DEBUG

public class LogUtils {

    public static float SCALEDATEX = 1;
    public static float SCALEDATEY = 1;
    public static float SCALEDFONT;
    /**
     * 界面参考缩放参数(宽)
     */
    public static float SCALEDATE_W = 0;
    /**
     * 界面参考缩放参数(高)
     */
    public static float SCALEDATE_H = 0;
    /**
     * 屏幕密度
     */
    public static float DM_DENSITY = 0;
    public static String phonenum;
    private static long lastClickTime;


    /**
     * 计算屏幕尺度
     *
     * @param activity
     */
    public static final void calculateScreenSize(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        float w = 320;
        float h = 480;
        float wRate = dm.widthPixels / w;
        float hRate = dm.heightPixels / h;
        setScaledParams(wRate, hRate);
        SCALEDFONT = 1.0F;
        int dw = dm.widthPixels;
        int dh = dm.heightPixels;
        float ds = dm.scaledDensity;

        if ((dw <= 240) && (dh <= 320)) {
            if (ds != 0.75F)
                SCALEDFONT = 0.75F / ds;
        } else if ((dw <= 320) && (dh <= 500)) {
            if (ds != 1.0F)
                SCALEDFONT = 1.0F / ds;
        } else if ((dw <= 480) && (dh <= 900)) {
            if (ds != 1.5F)
                SCALEDFONT = 1.5F / ds;
        } else if ((dw <= 600) && (ds != 2.0F))
            SCALEDFONT = 2.0F / ds;
    }

    /**
     * @param wRate 参照宽度
     * @param hRate
     */
    private final static void setScaledParams(float wRate, float hRate) {
        SCALEDATEX = wRate;
        SCALEDATEY = hRate;
    }

    /**
     * 计算界面宽高
     *
     * @param num
     * @return
     */
    public final static int getScaledValueX(float num) {
        float numtemp = SCALEDATEX * num;
        return (int) numtemp;
    }

    public final static int getScaledValueY(float num) {
        float numtemp = SCALEDATEY * num;
        return (int) numtemp;
    }

    /**
     * @param obj
     */
    public final static void print(Object obj) {
        // #ifdef DEBUG
//		if (null != obj) {
//			if (obj instanceof String) {
//				print((String) obj);
//			} else if (obj instanceof byte[]) {
//				print((byte[]) obj);
//			} else {
//				Utils.print(obj);
//			}
//		}
        // #endif
    }

    /**
     * @param s
     */
    public final static void print(String s) {
        // #ifdef DEBUG
        if (! BuildConfig.BUILD_TYPE.equals("debug")) {
            return;
        }
        if (null != s) {
            int length = s.length();
            int offset = 3000;
            // 解决报文过长，打印不全的问题
            if (length > offset) {
                int n = 0;
                for (int i = 0; i < length; i += offset) {
                    n += offset;
                    if (n > length) {
                        n = length;
                    }
                    System.err.println(Thread.currentThread().getName() + " -Debug = " + s.substring(i, n));
                }
            } else {
                System.err.println(Thread.currentThread().getName() + " - Debug = " + s);
            }
        }
        // #endif
    }

    /**
     * 获取资源图片
     *
     * @param context
     * @param resourcesName
     * @return
     */
    public final static Bitmap getBitmapFromResources(Context context, String resourcesName) {
        Bitmap bitmap = null;
        if (resourcesName == null)
            return bitmap;

        // 如果sql中没有存储对应的图片，则将后缀去掉，在资源文件里面搜索
        if (resourcesName.endsWith(".png")) {
            resourcesName = resourcesName.substring(0, resourcesName.lastIndexOf(".png"));
        }
        resourcesName = resourcesName.toLowerCase();
        int resourcesId = getResourcesId(context, resourcesName, "drawable");
        if (resourcesId != 0) {
            bitmap = getBitmap(resourcesId, context);
        }

        return bitmap;
    }

    /**
     * 获取资源id
     *
     * @param context
     * @param resourcesName
     * @param resourcesType
     * @return
     */
    public final static int getResourcesId(Context context, String resourcesName, String resourcesType) {
        int resourcesId = 0;
        resourcesId = context.getResources().getIdentifier(resourcesName, resourcesType, context.getPackageName());
        return resourcesId;
    }

    public final static Bitmap getBitmap(int rdoing, Context bv) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = bv.getResources().openRawResource(rdoing);

        Bitmap img = BitmapFactory.decodeStream(is, null, opt);
        try {
            is.close();
        } catch (IOException e) {
            printException(e);
        }
        return img;
    }

    /**
     * @param byts
     */
    public final static void print(byte[] byts) {
        // #ifdef DEBUG
        // if (byts == null) {
        // return;
        // }
        // for (int i = 0; i < byts.length; i++) {
        // System.out.print("[" + i + "]" + " : \t");
        // Utils.print(byts[i]);
        // }
        // #endif
    }

    public final static void printException(Exception e) {
        // #ifdef DEBUG
        // e.printStackTrace();
        // #endif
    }

    /**
     * 把bitmap转换成字节数组
     *
     * @param bmp
     * @param needRecycle
     * @return
     */
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            printException(e);
        }

        return result;
    }

//	public static String getPhoneNumber(Context context) {
//		TelephonyManager mTelephonyMgr;
//		mTelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//		// String phonenum = mTelephonyMgr.getLine1Number();
//		Utils.print("phonenum" + phonenum);
//		if (TextUtils.isEmpty(phonenum))
//			phonenum = "13144" + (int) (Math.random() * 10) + "1257" + (int) (Math.random() * 10);
//		return WalletApplication.phonenum;
//	}


    /**
     * 获取版本信息
     */
    public static String getSoftVersion(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo localPackageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return localPackageInfo.versionName;
        } catch (Exception e) {
            LogUtils.printException(e);
            return "";
        }

    }

    // public static String getHardInfo(Context context) {
    // TelephonyManager mTelephonyMgr;
    // mTelephonyMgr = (TelephonyManager)
    // context.getSystemService(Context.TELEPHONY_SERVICE);
    // String imei = mTelephonyMgr.getDeviceId();
    // return imei;
    // }


    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    // 版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    // 版本号
    public static int getVersionCode(Context context) {

        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return pi;
    }

    public static boolean isConnect(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();

        if (activeInfo == null) {
            return false;
        } else {
            return true;
        }
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String decToHex(int dec) {
        String hex = "";
        while (dec != 0) {
            String h = Integer.toString(dec & 0xff, 16);
            if ((h.length() & 0x01) == 1)
                h = '0' + h;
            hex = hex + h;
            dec = dec >> 8;
        }
        return hex;
    }

    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            //sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 用来判断服务是否运行.
     *
     * @param className 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningServiceInfo> myList = myAM.getRunningServices(100);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(className)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }

    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO8859-1"));
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }
        // toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
        return out.toString();
    }

    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 虚拟按键的高度
     *
     * @param context
     * @return
     */
    public static int getBottomStatusHeight(Context context) {
        int totalHeight = getDpi(context);

        int contentHeight = getScreenHeight(context);

        return (totalHeight - contentHeight) / 2;
    }

    /**
     * 标题栏高度
     *
     * @return
     */
    public static int getTitleHeight(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 获得屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取订单号
     *
     * @return 返回时间类型 yyyyMMddHHmmss+ 6位随机数
     */
    public static String getOrderNum() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentTime = new Date(System.currentTimeMillis());//获取当前时间
        String data = formatter.format(currentTime);
        int numcode = (int) ((Math.random() * 9 + 1) * 100000);
        StringBuffer sb = new StringBuffer().append(data).append(numcode);
        return sb.toString();
    }

    /**
     * 获取本地软件版本号
     */
    public static String getLocalVersion(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
            LogUtils.print("本软件的版本号->" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "v" + localVersion;
    }

}
