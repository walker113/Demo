package stay.walker.com.badge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;
import stay.walker.com.retrofitdemo.R;

public class BadgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item);
        new QBadgeView(this).bindTarget(findViewById(R.id.iv_pic)).setBadgeNumber(-1);
    }
}
