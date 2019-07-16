package stay.walker.com.kotlin.p

import com.google.gson.reflect.TypeToken
import stay.walker.com.kotlin.EntityCallBack
import stay.walker.com.kotlin.entity.Lesson
import stay.walker.com.kotlin.http.HttpClient
import stay.walker.com.kotlin.v.LessonActivity
import java.lang.reflect.Type

class LessonPresenter {
    companion object {
        const val LESSON_PATH = "lessons"
    }

    protected var activity: LessonActivity
    private var lessons: List<Lesson> = ArrayList()


    constructor(activity: LessonActivity) {
        this.activity = activity
    }

    val type: Type = object : TypeToken<List<Lesson>>(){}.type


    private fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallBack<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                this@LessonPresenter.lessons = entity
                activity.runOnUiThread {
                    activity.showResult(lessons)
                }
            }

            override fun onFailure(error: String) {
            }

        })
    }

    fun showPlayback(){
        val playBackLesson  = ArrayList<Lesson>()
        for (lesson in lessons) {
            if (lesson.state == Lesson.State.PLAYBACK) {
                playBackLesson.add(lesson)
            }
        }

    }





}