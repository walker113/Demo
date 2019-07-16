package stay.walker.com.web

import android.app.Activity
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

import stay.walker.com.retrofitdemo.R

class ShareView(private val mActivity: Activity) {
    // 分享页面的View
    private val mView: View

    init {
        //inflate XML布局
        this.mView = LayoutInflater.from(mActivity).inflate(R.layout.activity_visitor_code_face_pager, null)
    }

    /**
     * 设置mView中的数据
     */
    fun alreadyToGenerate() {
        val tv = mView.findViewById<View>(R.id.tv_visitor_name) as TextView
        tv.text = "哈哈哈哈哈哈"
    }

    /**
     * 生成图片核心代码
     */
    fun generateBitmap(): Bitmap {
        mView.isDrawingCacheEnabled = true
        //图片的宽度为屏幕宽度，高度为wrap_content
        mView.measure(View.MeasureSpec.makeMeasureSpec(mActivity.resources.displayMetrics.widthPixels, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))
        //放置mView
        mView.layout(0, 0, mView.measuredWidth, mView.measuredHeight)
        mView.buildDrawingCache()
        return mView.drawingCache
    }
}