package stay.walker.com.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import stay.walker.com.retrofitdemo.R;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.btn_get)
    TextView btnGet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jd);
        ButterKnife.bind(this);
        jumpActivity();
    }

    private void jumpActivity() {
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("欢迎大家浏览我写的博客"));
                finish();
            }
        });
    }

}
