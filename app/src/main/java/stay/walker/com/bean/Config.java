package stay.walker.com.bean;

/**
 * Created by huangzhebin on 16/7/21.
 * 全局参数类
 */
public class Config {
    //将同步内容下方到if内部，提高了执行的效率，不必每次获取对象时都进行同步，只有第一次才同步，创建了以后就没必要了。
    private static volatile Config instance = null;
    public static boolean isOpenLogger = false; //日志是否打开
//    public static boolean isOpenLogger = true;



    public static String APP_ID = "wx7d22712e67ec94d9";
    public static String wxreqUrl;
    public static String appId;


    /*地铁官方app用户信息查询参数*/

    /**
     * 测试环境
     */
    public static String metroUrl;
    public static String appsecret;
    public static String appkey;

//    /*************************************佳都-开发环境*****************************************************/
//    public EnmType enmType = EnmType.DEPOENM;    // 开发环境
//    public EnmType enmType = EnmType.TESTENM;   // 测试环境
    public static EnmType enmType = EnmType.PROENM;    //  生产环境
//	  public EnmType enmType = EnmType.GAMMA;


    public static String requestUrl = "";
    public static String terminalNo = "";
    public static String merchantNum = "";
    public static String key = "";

    public static String baseUrl = "";
    public static String apiUrl = "api/service";

    public static void initConfig() {
        switch (enmType) {
            case DEPOENM:
                Config.metroUrl = "http://112.124.3.146:7401/api/u/get";
                Config.appsecret = "arrlwxwpwvvqqehbiurdfqdl";
                Config.appkey = "keydemo";

                Config.requestUrl = "http://kfa.pcidata.cn:9930/api/service";
                Config.wxreqUrl = "http://kfa.pcidata.cn:9930/api/service";
                Config.appId = "WEI9X6B5Ca893gPPABkAAGFi";
//                UnionConfig.merchantNum = "GZ440100398";
                Config.merchantNum = "GZ001100547";
                Config.terminalNo = "OSSOTID1000603";
//                UnionConfig.terminalNo = "OSSOTID1000556";
//                UnionConfig.key = "uj0g05vj5sakl1303364e31q579m88a6";
                Config.key = "yb1ra614s38fs82jx34tsf565dj3x428";

                baseUrl = "http://kfa.pcidata.cn:9930/api/";
                break;

            case TESTENM:
                Config.metroUrl = "http://112.124.3.146:7401/api/u/get";
                Config.appsecret = "arrlwxwpwvvqqehbiurdfqdl";
                Config.appkey = "keydemo";
                Config.requestUrl = "https://w3c.pcidata.cn:20030/api/service";
//                UnionConfig.requestUrl = "https://121.8.250.85:20012/api/service";
                Config.appId = "WEI9X6B5Ca893gPPABkAAGFi";
                Config.wxreqUrl = "https://121.8.250.85:20012/api/service";//"https://121.8.250.85:20012/api/service";//"http://121.8.250.85:20007/api/service";
//                AILifeConfig.merchantNum = "GZ440100398";
//                AILifeConfig.terminalNo = "OSSOTID1000556";
//                AILifeConfig.key = "uj0g05vj5sakl1303364e31q579m88a6";

                Config.merchantNum = "YF071100600";
                Config.terminalNo = "OSSOTID1000777";
                Config.key = "6ud0f69ls61f7ssslnc66grh5yj489b1";

                baseUrl = "https://w3c.pcidata.cn:20030/";
                break;

            case PROENM:
//                地址：http://appv2.gzmtr.cn:7401/api/u/get
//                Ckey：safecheckjd
//                Publickey：xhiqecgvsdorabazihxpdzng
//                AILifeConfig.metroUrl = "http://appv2.gzmtr.cn:7401/api/u/get";
//                AILifeConfig.appsecret = "xhiqecgvsdorabazihxpdzng";
//                AILifeConfig.appkey = "safecheckjd";

                Config.metroUrl = "http://112.124.3.146:7401/api/u/get";
                Config.appsecret = "arrlwxwpwvvqqehbiurdfqdl";
                Config.appkey = "keydemo";
                Config.appId = "WEI9X6B5Ca893gPPABkAAGFi";
                Config.wxreqUrl = "https://api.pcidata.cn:30018/api/service";

                Config.requestUrl = "https://api.pcidata.cn:30018/api/service";
//                UnionConfig.merchantNum = "GZ001100560";
//                UnionConfig.terminalNo = "OSSOTID1000740";
//                UnionConfig.key = "01j275u6i73g1x90e999nmsu0n6dhwki";

                Config.merchantNum = "GZ001100092";
                Config.terminalNo = "OSSOTID1000201";
                Config.key = "nig779e31ve38gba600n3jq6f0816p9e";

                baseUrl = "https://api.pcidata.cn:30018/";
                break;

            case GAMMA:
//                UnionConfig.metroUrl = "http://112.124.3.146:7401/api/u/get";
//                UnionConfig.appsecret = "arrlwxwpwvvqqehbiurdfqdl";
//                UnionConfig.appkey = "keydemo";
//                UnionConfig.requestUrl = "https://w3c.pcidata.cn:20031/api/service";
//                UnionConfig.appId = "WEI9X6B5Ca893gPPABkAAGFi";
//                UnionConfig.wxreqUrl = "https://w3c.pcidata.cn:20031/api/service";
//                UnionConfig.key = "i27p8afu6pua72ekj0exk3d3mrgv5i08";
//                UnionConfig.merchantNum = "SZ002100341";
//                UnionConfig.terminalNo = "OSSOTID1000481";

                Config.requestUrl = "https://w3c.pcidata.cn:20031/api/service";
                Config.merchantNum = "GZ001100500";
                Config.terminalNo = "OSSOTID1000600";
                Config.key = "4h6sq0ee32322qyu0gt5oz7494ev7406";

                Config.metroUrl = "http://112.124.3.146:7401/api/u/get";
                Config.appsecret = "arrlwxwpwvvqqehbiurdfqdl";
                Config.appId = "WEI9X6B5Ca893gPPABkAAGFi";
                Config.wxreqUrl = "https://w3c.pcidata.cn:20031/api/service";
                Config.appkey = "keydemo";

                baseUrl = "https://w3c.pcidata.cn:20031/";
                break;

            case SHOWM:
                Config.metroUrl = "http://112.124.3.146:7401/api/u/get";
                Config.appsecret = "arrlwxwpwvvqqehbiurdfqdl";
                Config.appkey = "keydemo";
                Config.requestUrl = "http://w3c.pcidata.cn:58080/api/service";
                Config.appId = "WEI9X6B5Ca893gPPABkAAGFi";
                Config.wxreqUrl = "http://w3c.pcidata.cn:58080/api/service";
                Config.key = "i27p8afu6pua72ekj0exk3d3mrgv5i08";
                Config.merchantNum = "SZ002100341";
                Config.terminalNo = "OSSOTID1000481";
                break;
            default:
                break;
        }
    }


}
