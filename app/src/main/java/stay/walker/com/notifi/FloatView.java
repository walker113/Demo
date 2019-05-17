package stay.walker.com.notifi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import stay.walker.com.retrofitdemo.R;
import stay.walker.com.utils.DateUtil;

import static android.R.attr.width;


/**
 * 悬浮窗控件（解决滑动冲突）
 * Created by Devlin_n on 2017/6/8.
 */

@SuppressLint("ViewConstructor")
public class FloatView extends FrameLayout  {

    private Context mContext;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;

    private int DEFAULT_PADDING = 0;

    public FloatView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public FloatView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FloatView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        //这里可以设置padding
        mContext = context;
        setPadding(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING);
        initWindow();
        //这里设置你要显示的布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.notification_float, this);

        ((TextView) inflate.findViewById(R.id.tv_time)).setText(DateUtil.getFormat());
    }

    private void initWindow() {
        mWindowManager = WindowUtil.getWindowManager(getContext().getApplicationContext());
        mParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        }
//        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT; // 设置window type
        // 设置图片格式，效果为背景透明
        mParams.format = PixelFormat.TRANSLUCENT;
        //沉浸式的
        mParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_FULLSCREEN;
        mParams.windowAnimations = R.style.FloatWindowAnimation;//设置动画
        mParams.gravity = Gravity.CENTER_VERTICAL | Gravity.TOP; // 调整悬浮窗口至右下角
        // 设置悬浮窗口长宽数据
        mParams.width = mWindowManager.getDefaultDisplay().getWidth();
        mParams.height = width  / 4;
        //偏移为0
        mParams.x = 0;
        mParams.y = 0;
    }

    /**
     * 添加至窗口
     */
    public boolean addToWindow() {
        if (mWindowManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (!isAttachedToWindow()) {
                    mWindowManager.addView(this, mParams);
                    return true;
                } else {
                    return false;
                }
            } else {
                try {
                    if (getParent() == null) {
                        mWindowManager.addView(this, mParams);
                    }
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    /**
     * 从窗口移除
     */
    public boolean removeFromWindow() {
        if (mWindowManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (isAttachedToWindow()) {
                    mWindowManager.removeViewImmediate(this);
                    return true;
                } else {
                    return false;
                }
            } else {
                try {
                    if (getParent() != null) {
                        mWindowManager.removeViewImmediate(this);
                    }
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
        } else {
            return false;
        }
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//
//                //测试时点击即可消失
//                removeFromWindow();
//                Toast.makeText(mContext, "点击顶部通知栏,查看消息", Toast.LENGTH_SHORT).show();
//
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                break;
//        }
//        return true;
//    }
}
