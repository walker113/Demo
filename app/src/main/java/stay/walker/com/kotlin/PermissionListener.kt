package stay.walker.com.kotlin

interface PermissionListener {
    fun onGranted()

    fun onDenied(deniedPermissions: List<String>)

}