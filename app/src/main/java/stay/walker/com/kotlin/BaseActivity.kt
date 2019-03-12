package stay.walker.com.kotlin

open class BaseActivity : android.support.v7.app.AppCompatActivity() {

    private var mListener: PermissionListener? = null


    protected fun handlePermission(permissions: Array<String>?, listener: PermissionListener) {


    }
}