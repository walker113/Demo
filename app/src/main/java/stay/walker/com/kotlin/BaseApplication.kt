package stay.walker.com.kotlin

import android.app.Application
import android.content.Context


class BaseApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        currentApplication = this
    }


    companion object {
        private lateinit var currentApplication: Context


        fun currentApplication(): Context {
            return currentApplication
        }
    }

}