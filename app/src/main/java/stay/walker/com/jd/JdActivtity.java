package stay.walker.com.jd;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import stay.walker.com.bean.CloudLoginReq;
import stay.walker.com.bean.Config;
import stay.walker.com.bean.PhoneBindCardReq;
import stay.walker.com.bean.PhoneCardsDetail;
import stay.walker.com.dialog.InputDialog;
import stay.walker.com.eventbus.MessageEvent;
import stay.walker.com.eventbus.SecondActivity;
import stay.walker.com.retrofitdemo.R;
import stay.walker.com.sign.SignUtils;
import stay.walker.com.web.RHttpCallback;
import stay.walker.com.web.RNetUtils;

/**
 * @author admin
 */
public class JdActivtity extends BaseActivity {
    @BindView(R.id.tv_show)
    TextView tvShow;
    @BindView(R.id.btn_get)
    TextView btnGet;

    @OnClick(R.id.btn_get)
    public void open(View view) {
        ToastUtils.showShort("open");
        System.out.println("onClick");

        PhoneBindCardReq req = new PhoneBindCardReq(Config.merchantNum, Config.terminalNo);
        req.mobileNo = "18502076297";
        RNetUtils.doJdPost(
                SignUtils.getJsonParams(req, Config.key).toString(),
                this,
                new RHttpCallback<PhoneCardsDetail>() {
                    @Override
                    public PhoneCardsDetail convert(String data) {
                        return new Gson().fromJson(data, PhoneCardsDetail.class);
                    }

                    @Override
                    public void onSuccess(PhoneCardsDetail value) {

                    }


                    @Override
                    public void onError(String code, String desc) {

                    }

                    @Override
                    public void onCancel() {

                    }
                });

        Intent intent=new Intent(JdActivtity.this,SecondActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_cloud)
    void getCloud() {
//        CloudLoginReq.sendActionReq("PAYPCID1000136255", "18502076297");

        InputDialog.show(this, "hello", "kdls");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Config.initConfig();
        RNetUtils.initJdApi(Config.baseUrl, Config.apiUrl);
        setContentView(R.layout.activity_jd);
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        tvShow.setText(messageEvent.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
