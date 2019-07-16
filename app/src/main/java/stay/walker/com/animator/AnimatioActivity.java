package stay.walker.com.animator;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import stay.walker.com.retrofitdemo.R;

public class AnimatioActivity extends AppCompatActivity {


    CircleView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animator);


        view = findViewById(R.id.view);
//        // 很方便，但是只能坐系统规定的动画，平移、缩放、旋转等；
//        view.animate()
//                .setStartDelay(1000)
//                .translationX(500)
//                .rotation(180)
//                .start();

        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "radius", 300);
        animator.setStartDelay(1000);
        animator.start();

    }
}
