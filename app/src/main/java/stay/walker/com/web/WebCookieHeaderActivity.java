package stay.walker.com.web;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.cardemulation.CardEmulation;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import stay.walker.com.retrofitdemo.R;

/**
 * @author admin
 */
public class WebCookieHeaderActivity extends Activity {

    /**
     *
     * @param activity
     * @param isClick   是否点击 - 点击时弹出nfc切换选择
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean checkIsDefaultApp(Activity activity, boolean isClick) {
        try {
            CardEmulation cardEmulationManager = CardEmulation.getInstance(NfcAdapter.getDefaultAdapter(activity.getApplicationContext()));
            ComponentName paymentServiceComponent = new ComponentName(activity.getApplicationContext(), CommonHceService.class.getCanonicalName());
            if (!cardEmulationManager.isDefaultServiceForCategory(paymentServiceComponent, CardEmulation.CATEGORY_PAYMENT)) {
                if (isClick) {
                    Intent intent = new Intent(CardEmulation.ACTION_CHANGE_DEFAULT);
                    intent.putExtra(CardEmulation.EXTRA_CATEGORY, CardEmulation.CATEGORY_PAYMENT);
                    intent.putExtra(CardEmulation.EXTRA_SERVICE_COMPONENT, paymentServiceComponent);
                    activity.startActivityForResult(intent, 11);
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return true;
    }

    private WebView webView ;
    public static final String COOKIE_URL = "http://xilu.txby.site:20020/mobile/user.php";
    public static final String DISCOVER_URL = "http://xilu.txby.site:20020/mobile/goods.php?id=2631&now=1";
//    public static final String DISCOVER_URL = "http://baidu.com";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);

        webView = (WebView) findViewById( R.id.webview );

        syncCookie(COOKIE_URL,"SHOP[password]=31889dfb80c4a2ce77d782a166037abc");
        syncCookie(COOKIE_URL,"SHOP[user_id]=29527");

//        Map<String, String > map = new HashMap<String, String>() ;
//        map.put( "SHOP[password]" , "31889dfb80c4a2ce77d782a166037abc" ) ;
//        map.put( "SHOP[user_id]" , "29527" ) ;
//        webView.loadUrl( DISCOVER_URL  , map ) ;

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("TAG", "url = " +url);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Log.e("TAG", error.toString());
                super.onReceivedError(view, request, error);
            }
        });
//        ws.setDefaultFontSize(16);
        //设置缓存模式
        webView.loadUrl(DISCOVER_URL);

        checkIsDefaultApp(this, true);
    }

    public boolean syncCookie(String url, String cookie) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(getApplicationContext());
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setCookie(url, cookie);//如果没有特殊需求，这里只需要将session id以"key=value"形式作为cookie即可
        String newCookie = cookieManager.getCookie(url);
        Log.e("TAG", newCookie);
        return TextUtils.isEmpty(newCookie) ? false : true;
    }


}
