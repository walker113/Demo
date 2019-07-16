package stay.walker.com.kotlin.util

import android.content.res.Resources
import android.util.TypedValue

// 顶层函数
fun dpxdx(dp: Float): Float {
    val displayMetrics = Resources.getSystem().displayMetrics;
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}