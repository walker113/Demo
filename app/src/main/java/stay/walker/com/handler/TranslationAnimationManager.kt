package stay.walker.com.handler

import android.animation.ValueAnimator
import android.view.View

class TranslationAnimationManager(val startTranslateX: Float, val endTranslationX: Float,
                                  val startTranslateY: Float, val maxTranslationY: Float) :AnimationInterface {


    var view: View? = null
    private var animator: ValueAnimator? = null
    private var onAnimationUpdate: AnimationUpdate? = null


    override fun startAnimation() {

        view.let {

            if (animator == null) {

            }
        }



    }

    override fun stopAnimation() {
    }

    override fun cancelAnimation() {
    }

    override fun setAnimationUpdate(animationUpdate: AnimationUpdate) {
    }





}