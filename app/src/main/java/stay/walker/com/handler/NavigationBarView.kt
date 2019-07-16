package stay.walker.com.handler

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast

class NavigationBarView : ViewGroup {

    //导航的总宽度
    private var navigationWidth = 0
    //导航某一项的宽度
    private var navigationButtonWidth = 0
    //导航上部的高度
    private var navigationTop = 0
    //导航底部的高度
    private var navigationBottom = 0
    //导航的总高度
    private var navigationHeight = 0


    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {

    }


    private val navigationButtonListView = ArrayList<View>()

    //当前选中的是第几项
    private var currentIndex = -1

    fun addImageButton(views: List<View>) {
        for (i in 0 until views.size) {
            val frameLayout = FrameLayout(context)
            val view = views.get(i)
            super.addView(frameLayout)

            navigationButtonListView.add(frameLayout)
            frameLayout.addView(view)

            val params = view.layoutParams as FrameLayout.LayoutParams
            params.gravity = Gravity.CENTER

            frameLayout.setOnClickListener {
                if (currentIndex == i) {
                    return@setOnClickListener
                }

                currentIndex = i
                Toast.makeText(context, i.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        if (childCount <= 0) {
            return
        }

        for (i in 0 until navigationButtonListView.size) {
            val view = navigationButtonListView.get(i)
            view.layout(
                    i* navigationButtonWidth,
                    navigationTop,
                    i* navigationButtonWidth + navigationButtonWidth,
                    navigationTop + navigationBottom)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        measureChildren(widthMeasureSpec, heightMeasureSpec)

        navigationWidth = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), navigationHeight)
    }

}