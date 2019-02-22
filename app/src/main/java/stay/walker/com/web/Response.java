package stay.walker.com.web;

import com.google.gson.annotations.SerializedName;

public class Response {

    /**
     * 交易码
     */
    @SerializedName("tranCode")
    private String tranCode;

    /**
     * 返回码
     * 成功：”000000”，失败返回：“错误码”
     */
    @SerializedName("resp_code")
    private String resp_code;

    /**
     * 返回信息
     * 成功：不返回，失败：返回失败原因
     */
    @SerializedName("resp_msg")
    private String resp_msg;

    public String getTranCode() {
        return tranCode;
    }

    public String getResp_code() {
        return resp_code;
    }

    public String getResp_msg() {
        return resp_msg;
    }

    /**
     * 是否成功
     *
     * @return
     */
    public boolean isSuccess() {
        return resp_code.equals("000000");
    }
}