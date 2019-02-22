package stay.walker.com.web;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.r.http.cn.callback.HttpCallback;

public abstract class RHttpCallback<T> extends HttpCallback<T> {

    private Response response;

    @Override
    public T onConvert(String data) {
        /**
         * 接口响应数据格式如@Response
         * 根据业务封装:
         * 1. response.isSuccess() (code==0) 业务逻辑成功回调convert()=>onSuccess()，否则失败回调onError()
         * 2.统一处理接口逻辑 例如:code==101 token过期等等
         */
        T t = null;
        response = new Gson().fromJson(data, Response.class);
        String code = response.getResp_code();
        String msg = response.getResp_msg();

        if (response.isSuccess()) {
            t = convert(data);
        } else {
            onError(code, msg);
        }
        return t;
    }

    /**
     * 数据转换/解析
     *
     * @param data
     * @return
     */
    public abstract T convert(String data);

    /**
     * 成功回调
     *
     * @param value
     */
    @Override
    public abstract void onSuccess(T value);


    @Override
    public void onError(int i, String s) {

    }

    /**
     * 失败回调
     *
     * @param code
     * @param desc
     */
    public abstract void onError(String code, String desc);

    /**
     * 取消回调
     */
    @Override
    public abstract void onCancel();

    /**
     * 业务逻辑是否成功
     *
     * @return
     */
    @Override
    public boolean isBusinessOk() {
        return response.isSuccess();
    }


}