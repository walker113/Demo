package stay.walker.com.bean;

import java.util.List;

public class PhoneCardsDetail {
    /**
     * iBindList : [{"mTerminalNo":"OSSOTID1000556","accountNo":"PAYPACCID05CNY10000004534","customerId":"PAYPCID2000001900","cardType":null,"shopNo":"GZ440100398","mKey":"uj0g05vj5sakl1303364e31q579m88a6"},{"mTerminalNo":"OSSOTID1000556","accountNo":"PAYPACCID22CNY10000004533","customerId":"PAYPCID2000001900","cardType":null,"shopNo":"GZ440100398","mKey":"uj0g05vj5sakl1303364e31q579m88a6"},{"mTerminalNo":"OSSOTID1000556","accountNo":"PAYPACCID11CNY10000004532","customerId":"PAYPCID2000001900","cardType":null,"shopNo":"GZ440100398","mKey":"uj0g05vj5sakl1303364e31q579m88a6"},{"mTerminalNo":"OSSOTID1000556","accountNo":"PAYPACCID04CNY10000004531","customerId":"PAYPCID2000001900","cardType":null,"shopNo":"GZ440100398","mKey":"uj0g05vj5sakl1303364e31q579m88a6"},{"mTerminalNo":"OSSOTID1000556","accountNo":"PAYPACCID03CNY10000004530","customerId":"PAYPCID2000001900","cardType":null,"shopNo":"GZ440100398","mKey":"uj0g05vj5sakl1303364e31q579m88a6"},{"mTerminalNo":"OSSOTID1000556","accountNo":"3320DC7F2D2AE182","customerId":"PAYPCID2000001900","cardType":"28","shopNo":"GZ440100398","mKey":"uj0g05vj5sakl1303364e31q579m88a6"},{"mTerminalNo":"OSSOTID1000556","accountNo":"0000280011000716","customerId":"PAYPCID2000001900","cardType":"28","shopNo":"GZ440100398","mKey":"uj0g05vj5sakl1303364e31q579m88a6"},{"mTerminalNo":"OSSOTID1000015","accountNo":"PAYPACCID05CNY10000004321","customerId":"PAYPCID2000001856","cardType":null,"shopNo":"OSSOSIDBSOS10000000019","mKey":"897j6g8y55b5k365g7v954d50u24h14f"},{"mTerminalNo":"OSSOTID1000015","accountNo":"PAYPACCID11CNY10000004320","customerId":"PAYPCID2000001856","cardType":null,"shopNo":"OSSOSIDBSOS10000000019","mKey":"897j6g8y55b5k365g7v954d50u24h14f"},{"mTerminalNo":"OSSOTID1000015","accountNo":"PAYPACCID04CNY10000004319","customerId":"PAYPCID2000001856","cardType":null,"shopNo":"OSSOSIDBSOS10000000019","mKey":"897j6g8y55b5k365g7v954d50u24h14f"},{"mTerminalNo":"OSSOTID1000015","accountNo":"PAYPACCID03CNY10000004318","customerId":"PAYPCID2000001856","cardType":null,"shopNo":"OSSOSIDBSOS10000000019","mKey":"897j6g8y55b5k365g7v954d50u24h14f"},{"mTerminalNo":"OSSOTID1000436","accountNo":"PAYPACCID22CNY10000004565","customerId":"PAYPCID2000001857","cardType":null,"shopNo":"GZ440100082","mKey":"9qga1r7hv170d43uea9ogebf9e69t05c"},{"mTerminalNo":"OSSOTID1000436","accountNo":"FED6009EBBBE5FA9","customerId":"PAYPCID2000001857","cardType":"14","shopNo":"GZ440100082","mKey":"9qga1r7hv170d43uea9ogebf9e69t05c"},{"mTerminalNo":"OSSOTID1000436","accountNo":"PAYPACCID05CNY10000004325","customerId":"PAYPCID2000001857","cardType":null,"shopNo":"GZ440100082","mKey":"9qga1r7hv170d43uea9ogebf9e69t05c"},{"mTerminalNo":"OSSOTID1000436","accountNo":"PAYPACCID11CNY10000004324","customerId":"PAYPCID2000001857","cardType":null,"shopNo":"GZ440100082","mKey":"9qga1r7hv170d43uea9ogebf9e69t05c"},{"mTerminalNo":"OSSOTID1000436","accountNo":"PAYPACCID04CNY10000004323","customerId":"PAYPCID2000001857","cardType":null,"shopNo":"GZ440100082","mKey":"9qga1r7hv170d43uea9ogebf9e69t05c"},{"mTerminalNo":"OSSOTID1000436","accountNo":"PAYPACCID03CNY10000004322","customerId":"PAYPCID2000001857","cardType":null,"shopNo":"GZ440100082","mKey":"9qga1r7hv170d43uea9ogebf9e69t05c"},{"mTerminalNo":"OSSOTID1000603","accountNo":"0000080031000245","customerId":"PAYPCID2000001933","cardType":"32","shopNo":"GZ001100547","mKey":"yb1ra614s38fs82jx34tsf565dj3x428"},{"mTerminalNo":"OSSOTID1000603","accountNo":"PAYPACCID05CNY10000004700","customerId":"PAYPCID2000001933","cardType":null,"shopNo":"GZ001100547","mKey":"yb1ra614s38fs82jx34tsf565dj3x428"},{"mTerminalNo":"OSSOTID1000603","accountNo":"PAYPACCID22CNY10000004699","customerId":"PAYPCID2000001933","cardType":null,"shopNo":"GZ001100547","mKey":"yb1ra614s38fs82jx34tsf565dj3x428"},{"mTerminalNo":"OSSOTID1000603","accountNo":"PAYPACCID11CNY10000004698","customerId":"PAYPCID2000001933","cardType":null,"shopNo":"GZ001100547","mKey":"yb1ra614s38fs82jx34tsf565dj3x428"},{"mTerminalNo":"OSSOTID1000603","accountNo":"PAYPACCID04CNY10000004697","customerId":"PAYPCID2000001933","cardType":null,"shopNo":"GZ001100547","mKey":"yb1ra614s38fs82jx34tsf565dj3x428"},{"mTerminalNo":"OSSOTID1000603","accountNo":"PAYPACCID03CNY10000004696","customerId":"PAYPCID2000001933","cardType":null,"shopNo":"GZ001100547","mKey":"yb1ra614s38fs82jx34tsf565dj3x428"}]
     * resp_code : 000000
     * tranCode : pay_action_000084
     * resp_msg : success
     */

    private String resp_code;
    private String tranCode;
    private String resp_msg;
    private List<IBindListBean> iBindList;

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    public String getResp_msg() {
        return resp_msg;
    }

    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }

    public List<IBindListBean> getIBindList() {
        return iBindList;
    }

    public void setIBindList(List<IBindListBean> iBindList) {
        this.iBindList = iBindList;
    }

    public static class IBindListBean {
        /**
         * mTerminalNo : OSSOTID1000556
         * accountNo : PAYPACCID05CNY10000004534
         * mCustomerId : PAYPCID2000001900
         * cardType : null
         * shopNo : GZ440100398
         * mKey : uj0g05vj5sakl1303364e31q579m88a6
         */
        private String terminalNo;
        private String accountNo;
        private String customerId;
        private String cardType;
        private String shopNo;
        private String key;

        public String getTerminalNo() {
            return terminalNo;
        }

        public void setTerminalNo(String terminalNo) {
            this.terminalNo = terminalNo;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public void setAccountNo(String accountNo) {
            this.accountNo = accountNo;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public String getShopNo() {
            return shopNo;
        }

        public void setShopNo(String shopNo) {
            this.shopNo = shopNo;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
