package stay.walker.com.kotlin.v

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import stay.walker.com.kotlin.BaseView
import stay.walker.com.kotlin.entity.Lesson
import stay.walker.com.kotlin.p.LessonPresenter

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class LessonActivity
    : AppCompatActivity(),
        BaseView<LessonPresenter>, Toolbar.OnMenuItemClickListener {
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getPresenter(): LessonPresenter {
        return LessonPresenter(this)
    }

    fun showResult(lessons: List<Lesson>) {


    }


}