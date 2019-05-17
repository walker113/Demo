package stay.walker.com.notifi;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class FloatViewManager {

    private static FloatViewManager instance;
    private FloatView floatView;
    private boolean isShowing;

    private FloatViewManager() {
        floatView = new FloatView(AndroidUtils.getApp());
    }

    public static FloatViewManager getInstance() {
        if (instance == null) {
            synchronized (FloatViewManager.class) {
                if (instance == null) {
                    instance = new FloatViewManager();
                }
            }
        }
        return instance;
    }

    public void startFloatWindow() {
        Log.e("TAG", "开启悬浮窗");
        if (!isStartFloatWindow() && floatView != null) {
            floatView.addToWindow();
            isShowing = true;
            //发送一个Handel 让当前的弹框消失
            dismissHandler.sendEmptyMessageDelayed(1, 3000);
        }
    }

    public void stopFloatWindow() {
        if (isStartFloatWindow()) {
            floatView.removeFromWindow();
            isShowing = false;
        }
    }

    public boolean isStartFloatWindow() {
        return isShowing;
    }


    //这里暂时没有处理handle内存泄露问题
    private Handler dismissHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                Log.e("TAG", "主动关闭");
                //显示2秒之后自动消失
                stopFloatWindow();
                dismissHandler.removeMessages(1);
                onDestory();
            }
        }
    };

    /**
     * 消失 ,并且防止内存泄露
     */
    public void onDestory() {
        if (dismissHandler != null) {
            dismissHandler.removeMessages(1);
            dismissHandler.removeCallbacksAndMessages(null);
        }
        stopFloatWindow();
    }




}
