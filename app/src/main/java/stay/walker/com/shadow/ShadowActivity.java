package stay.walker.com.shadow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.method.MetaKeyKeyListener;
import android.view.View;
import android.widget.TextView;

import com.socks.library.KLog;

import stay.walker.com.retrofitdemo.R;

public class ShadowActivity extends AppCompatActivity {


    private TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shadow_layout);


        tv = findViewById(R.id.renlian_new_tv_home);
    }

    public void doShow(View view) {
        LoadingDialog dialog = new LoadingDialog();
        dialog.show(getSupportFragmentManager(), "TAG");
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
