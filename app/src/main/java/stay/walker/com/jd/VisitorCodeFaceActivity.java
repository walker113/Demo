package stay.walker.com.jd;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.socks.library.KLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import stay.walker.com.notifi.FloatPermissionManager;
import stay.walker.com.notifi.FloatViewManager;
import stay.walker.com.retrofitdemo.R;
import stay.walker.com.web.ShareView;

/**
 * @author admin
 */
public class VisitorCodeFaceActivity extends AppCompatActivity {

    private ImageView mIvVIew;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_visitor_pager);

        mIvVIew = findViewById(R.id.security_qr_code_iv);
        findViewById(R.id.tv_aid_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                createQQTopNotification(VisitorCodeFaceActivity.this);


                ShareView shareView = new ShareView(VisitorCodeFaceActivity.this);
                shareView.alreadyToGenerate();
                mIvVIew.setImageBitmap(shareView.generateBitmap());
                KLog.e("----");
            }
        });



    }

    private void notifyShow() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 通知渠道的id
            String id = getString(R.string.notification_channel_id);
            // 用户可以看到的通知渠道的名字.
            CharSequence name = getString(R.string.notification_channel_name);
            // 用户可以看到的通知渠道的描述
            String description = getString(R.string.notification_channel_desc);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            //注意Name和description不能为null或者""
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(false);
            mChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            notificationManager.createNotificationChannel(mChannel);
            builder.setChannelId(id);
        }
        builder.setContentText("打卡");
        builder.setContentTitle("佳都科技自动打卡成功");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("新消息");
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        Intent intent = new Intent(this, VisitorCodeFaceActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_CANCEL_CURRENT);
//        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();
        notificationManager.notify(1, notification);
    }

    private void notification() {
        String id = "my_channel_01";
        String name="我是渠道名字";
        final Intent intent = new Intent();
        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
            Toast.makeText(this, mChannel.toString(), Toast.LENGTH_SHORT).show();
            notificationManager.createNotificationChannel(mChannel);
//            notification = new Notification.Builder(this)
//                    .setChannelId(id)
//                    .setAutoCancel(true)
//                    .setContentIntent(pendingIntent)
//                    .setFullScreenIntent(pendingIntent, true)
//                    .setContentTitle("message")
//                    .setContentText("hahaha")
//                    .setSmallIcon(R.mipmap.ic_launcher).build();
            notification = new NotificationCompat
                    .Builder(this)
                    .setChannelId(id)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("message")
                    .setContentText("hahaha")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setFullScreenIntent(pendingIntent, true)
                    .build();
        } else {
//            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                    .setContentTitle("5 new messages")
//                    .setContentIntent(pendingIntent)
//                    .setFullScreenIntent(pendingIntent, true)
//                    .setContentText("hahaha")
//                    .setDefaults(Notification.DEFAULT_VIBRATE)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setOngoing(true);
//            notification = notificationBuilder.build();

            notification = new NotificationCompat
                    .Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("message")
                    .setContentText("hahaha")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_VIBRATE)
                    .setFullScreenIntent(pendingIntent, true)
                    .build();
        }
        notificationManager.notify(111123, notification);

    }


    /**
     * 这里是开启类似QQ一样的悬挂式的通知
     *
     * @param context
     */
    public void createQQTopNotification(Context context) {
//        if (FloatPermissionManager.getInstance().checkPermission(context)) {
//            //开启悬挂
//            notifyShow();
//        } else {
//            Toast.makeText(context, "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
//            FloatPermissionManager.getInstance().applyPermission(context);
//        }

//        notifyShow();

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        String path = getExternalCacheDir() + File.separator + "aa.jpg";
        saveBitmap(shotActivityNoBar(this), path);
    }

    public Bitmap shotActivityNoBar(Activity activity) {
        // 获取windows中最顶层的view
        View view = activity.getWindow().getDecorView();
        view.buildDrawingCache();

        // 获取状态栏高度
        Rect rect = new Rect();
        view.getWindowVisibleDisplayFrame(rect);
        int statusBarHeights = rect.top;
        Display display = activity.getWindowManager().getDefaultDisplay();

        // 获取屏幕宽和高
        int widths = display.getWidth();
        int heights = display.getHeight();

        // 允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);

        // 去掉状态栏
        Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache(), 0,
                statusBarHeights, widths, heights - statusBarHeights);

        // 销毁缓存信息
        view.destroyDrawingCache();

        return bmp;
    }


    public static void saveBitmap(Bitmap bitmap, String path) {
        File file = new File(path);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            System.out.println("___________保存的__sd___下_______________________");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
