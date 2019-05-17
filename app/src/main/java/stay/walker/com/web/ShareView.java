package stay.walker.com.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import stay.walker.com.retrofitdemo.R;

public  class ShareView {
    // 分享页面的View
    private View mView;
    private Activity mActivity;

    public ShareView(Activity activity) {
        this.mActivity = activity;
        //inflate XML布局
        this.mView = LayoutInflater.from(activity).inflate(R.layout.activity_visitor_code_face_pager, null);
    }

    /**
     * 设置mView中的数据
     */
    public void alreadyToGenerate() {
        TextView tv = (TextView) mView.findViewById(R.id.tv_visitor_name);
        tv.setText("哈哈哈哈哈哈");
    }

    /**
     * 生成图片核心代码
     */
    public Bitmap generateBitmap() {
        mView.setDrawingCacheEnabled(true);
        //图片的宽度为屏幕宽度，高度为wrap_content
        mView.measure(View.MeasureSpec.makeMeasureSpec(mActivity.getResources().getDisplayMetrics().widthPixels, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //放置mView
        mView.layout(0, 0, mView.getMeasuredWidth(), mView.getMeasuredHeight());
        mView.buildDrawingCache();
        Bitmap bitmap = mView.getDrawingCache();
        return bitmap;
    }
}