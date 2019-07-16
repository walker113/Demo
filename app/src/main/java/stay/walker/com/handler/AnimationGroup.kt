package stay.walker.com.handler

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout


class AnimationGroup : FrameLayout {
    constructor(context: Context?) : super(context){
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        init()
    }



    private fun init() {


    }
}