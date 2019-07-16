package stay.walker.com.coupons

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.socks.library.KLog
import q.rorbin.badgeview.QBadgeView
import stay.walker.com.retrofitdemo.R


class CouponsActivity : AppCompatActivity() {

    private lateinit var mTvGetIt: TextView
    private lateinit var mTvPrice: TextView
    private lateinit var mTvDesc: TextView
    private lateinit var mIvBlue: ImageView
    private lateinit var mIvPic: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.center)

//        initView()
    }

    override fun onResume() {
        super.onResume()

    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

//        print(mIvBlue)
//        print(mIvPic)


    }




    fun print(view: View) {
        Log.e("TAG", "w = " + view.width + ", h = " + view.height)
    }

    private fun initView() {
        mTvGetIt = findViewById(R.id.tv_get_it)
        mTvPrice = findViewById(R.id.tv_price)
        mTvDesc = findViewById(R.id.tv_desc)
        mIvBlue = findViewById(R.id.iv_blue)
        mIvPic = findViewById(R.id.iv_pic)


        QBadgeView(this).bindTarget(mIvBlue).setBadgeNumber(-1)
    }


}