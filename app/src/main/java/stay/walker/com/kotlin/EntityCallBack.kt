package stay.walker.com.kotlin


interface EntityCallBack<T> {
    fun onSuccess(t: T)
    fun onFailure(error: String)
}