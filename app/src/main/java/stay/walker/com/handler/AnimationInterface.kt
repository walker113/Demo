package stay.walker.com.handler


interface AnimationInterface {

    fun startAnimation()
    fun stopAnimation()
    fun cancelAnimation()
    fun setAnimationUpdate(animationUpdate: AnimationUpdate)

}

interface AnimationUpdate {
    fun onAnimationUpdate(precent: Float)
}