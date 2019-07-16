package stay.walker.com.web;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;


import com.just.agentweb.JsInterfaceHolder;
import com.socks.library.KLog;

import org.json.JSONObject;

import stay.walker.com.retrofitdemo.R;
import stay.walker.com.sign.MD5;

/**
 * 通用WebView Activity
 * @date 2019年1月14日
 * @author 009632
 */
public class CommonWebActivity extends BaseAgentWebActivity {

    private TextView mTitleTextView;

    /**
     * 跳转url
     */
    private String url;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_web);
//        setCookie();
        Toolbar mToolbar = this.findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(Color.BLACK);
        mToolbar.setTitle("");
        mTitleTextView = this.findViewById(R.id.toolbar_title);
        mTitleTextView.setText(mTitle);
        this.setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAgentWeb.getJsAccessEntrace().quickCallJs(
                        "callByAndroidMoreParams",
                        new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.i("Info","value:"+value);
                    }
                },getJson(),
                        "say:",
                        " Hello! Agentweb");
            }
        });


        AndroidInterface androidInterface = new AndroidInterface();
        JsInterfaceHolder android =
                mAgentWeb.getJsInterfaceHolder().addJavaObject("android", androidInterface);
        setUserAgent();
        KLog.e("checkObject - " + android.checkObject(androidInterface));

        KLog.e(MD5.encode("0112345627乐生活乐生活 001").toUpperCase());
    }



    private class AndroidInterface {
        @JavascriptInterface
        public void jumpZYT() {
        }

        @JavascriptInterface
        public void coupon(int i) {
            KLog.e(i);
        }
    }


    private String getJson(){

        String result="";
        try {

            JSONObject mJSONObject=new JSONObject();
            mJSONObject.put("id",1);
            mJSONObject.put("name","Agentweb");
            mJSONObject.put("age",18);
            result= mJSONObject.toString();
        }catch (Exception e){

        }

        return result;
    }


    @NonNull
    @Override
    protected ViewGroup getAgentWebParent() {
        return (ViewGroup) this.findViewById(R.id.container);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mAgentWeb != null && mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void setTitle(WebView view, String title) {
        if (TextUtils.isEmpty(mTitle)) {
            mTitleTextView.setText(title);
        }
    }

    @Override
    protected int getIndicatorColor() {
        return Color.parseColor("#ff0000");
    }

    @Override
    protected int getIndicatorHeight() {
        return 3;
    }

    @Override
    protected String getUrl() {
        return "http://172.20.15.240:8080/?act=eticket&rd=9aQkXjuR29Qu%2FJ2WUd0%2FEc3Ga18EksRpm2hDn8eB2c6EhCAns4SCqExgUox%2Fv3Pbpzca51ezbNh98uzz0LOvRZT26%20y8aJRQv%2FXnYucnyYakVErtgmqJ%2FnMk9E9hwaGN&rs=efd1c1b32dd76310d6931e305a16e0cbd0fcac494e819aad3e8b4a8c383564d1#/";
//        return "https://m.vip.com/?source=www&jump_https=1";
    }



    @Nullable
    @Override
    protected WebChromeClient getWebChromeClient() {
        return mWebChromeClient;
    }

    protected WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (mTitleTextView != null && !TextUtils.isEmpty(title)) {
                if (title.length() > 10) {
                    title = title.substring(0, 10).concat("...");
                }
            }
            if (TextUtils.isEmpty(mTitle)) {
                mTitleTextView.setText(title);
            }
        }
    };


    /**
     * 电子票跳转需要设置UA
     */
    private void setUserAgent() {
        if (mAgentWeb == null ||
                mAgentWeb.getAgentWebSettings() == null ||
                mAgentWeb.getAgentWebSettings().getWebSettings() == null) {
            return;
        }
        mAgentWeb.getAgentWebSettings().getWebSettings().setUserAgentString(E_TICKET_AGENT);
    }

    public static final String E_TICKET_AGENT = "Pcidata/1.0.5 Mozilla/5.0 (Linux; Android; iPhone; CPU iPhone OS 10_3_2 like Mac OS X) AppleWebKit/603.2.4 (KHTML, like Gecko) Mobile/14F89)";
}