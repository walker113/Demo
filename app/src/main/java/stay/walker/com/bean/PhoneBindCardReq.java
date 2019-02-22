package stay.walker.com.bean;


/**
 * 手机号绑定云卡列表接口
 * @author 009632
 */
public class PhoneBindCardReq {

    public String
            tranCode = "pay_action_000084",
            shopNo,
            terminalNo,
            channelFlag = "02",
            mobileNo,
            cardType,
            sign;

    public PhoneBindCardReq(String shopNo, String terminalNo) {
        this.shopNo = shopNo;
        this.terminalNo = terminalNo;
    }

}
