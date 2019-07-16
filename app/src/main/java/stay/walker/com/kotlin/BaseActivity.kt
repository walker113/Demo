package stay.walker.com.kotlin

import android.content.res.Resources
import android.util.TypedValue

open class BaseActivity : android.support.v7.app.AppCompatActivity() {

    private var mListener: PermissionListener? = null


    protected fun handlePermission(permissions: Array<String>?, listener: PermissionListener) {


        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, Resources.getSystem().displayMetrics)
    }
}