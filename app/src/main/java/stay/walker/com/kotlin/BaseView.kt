package stay.walker.com.kotlin

interface BaseView<T> {
    fun getPresenter(): T

}