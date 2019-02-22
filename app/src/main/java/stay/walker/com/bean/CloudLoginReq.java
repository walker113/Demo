package stay.walker.com.bean;

import android.text.TextUtils;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stay.walker.com.sign.LogUtils;
import stay.walker.com.sign.MD5;
import stay.walker.com.web.RHttpCallback;
import stay.walker.com.web.RNetUtils;

/**
 * 云商登录接口
 *
 * @author 009632
 */
public class CloudLoginReq {

    private static final String secret = "K0IKVix0MDAr4MaMu3Q4Zg";
    private String
            act = "sigin",
            password,
            username,
            key;


    public static void sendActionReq(final String customerId, final String userName) {
        final CloudLoginReq request = new CloudLoginReq();
        if (TextUtils.isEmpty(userName)) {
            return;
        }
        request.username = userName;
        request.password = userName.substring(5);

        List<String> params = new ArrayList<>();
        params.add(request.act);
        params.add(request.password);
        params.add(request.username);
        params.add(CloudLoginReq.secret);
        StringBuilder builder = new StringBuilder();
        for (String field : params) {
            builder.append(field);
        }
        LogUtils.print(builder.toString());
        request.key = MD5.encode(builder.toString());

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Map<String, String> reqParam = new HashMap<>();
//                reqParam.put("act", request.act);
//                reqParam.put("key", request.key);
//                reqParam.put("password", request.password);
//                reqParam.put("username", request.username);
//                LogUtils.print("云商登录 : " + reqParam.toString());
//                JSONObject results = HttpUtil.sendPost(Config.cloudBusinessUrl, reqParam);
//                try {
//                    // PHP后台报错是返回result格式是[],  {"code":"0x011","info":"登陆失败","result":[]}
//                    if (results != null && results.getString("code").equals("0x000")) {
//                        Gson gson = new Gson();
//                        CloudBusinessResp resp = gson.fromJson(results.toString(), CloudBusinessResp.class);
//                        SPHelper.setCloudShopId(SPHelper.CLOUD_SHOP_ID + resp.getResult().getUser_id());
//                        SPHelper.setCloudShopPasswd(SPHelper.CLOUD_SHOP_PASSWD + resp.getResult().getPassword());
//                    }
//                    else {
//                        CloudRegisterReq.sendActionReq(customerId, userName);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        Map<String, String> reqParam = new HashMap<>();
        reqParam.put("act", request.act);
        reqParam.put("key", request.key);
        reqParam.put("password", request.password);
        reqParam.put("username", request.username);
        LogUtils.print("云商登录 : " + reqParam.toString());
// {password=076297, act=sigin, key=120d608d84b321847b07194820699f78, username=18502076297}
        RNetUtils.doJdPost(
                "http://xilu.txby.site:20020/",
                "mobile/app.php",
                reqParam,
                null,
                new RHttpCallback() {
                    @Override
                    public String onConvert(String data) {
                        convert(data);
                        return data;
                    }

                    @Override
                    public Object convert(String data) {
                        KLog.i(data);
                        return null;
                    }

                    @Override
                    public void onSuccess(Object value) {

                    }

                    @Override
                    public void onError(String code, String desc) {

                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public boolean isBusinessOk() {
                        return true;
                    }
                });

    }

}
