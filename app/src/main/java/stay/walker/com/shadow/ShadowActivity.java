package stay.walker.com.shadow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.MetaKeyKeyListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.socks.library.KLog;

import stay.walker.com.retrofitdemo.R;
import stay.walker.com.shadow.guideview.Component;
import stay.walker.com.shadow.guideview.Guide;
import stay.walker.com.shadow.guideview.GuideBuilder;

public class ShadowActivity extends AppCompatActivity {


    private TextView tv;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shadow_layout);
        tv = findViewById(R.id.renlian_new_tv_home);
        imageView = findViewById(R.id.iv_face);
    }

    Guide guide;

    public void doShow(View view) {
//        LoadingDialog dialog = new LoadingDialog();
//        dialog.show(getSupportFragmentManager(), "TAG");


        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(imageView)
                .setAlpha(150)
                .setHighTargetPadding(20)
                .setOverlayTarget(false)
                .setOutsideTouchable(false)
                .setHighTargetGraphStyle(Component.CIRCLE);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override public void onShown() {
            }

            @Override public void onDismiss() {

            }
        });

        builder.addComponent(new SimpleComponent());
        guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(ShadowActivity.this);
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        KLog.e(tv.getWidth() + ", " + tv.getX());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        KLog.e("Top     = " + tv.getTop() + ", x = " + tv.getX());
        KLog.e("Left    = " + tv.getLeft() + ", y = " + tv.getY());
    }
}
