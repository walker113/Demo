package stay.walker.com.retrofitdemo.messge;

import stay.walker.com.retrofitdemo.dialog.LoadingDialog;
import stay.walker.com.retrofitdemo.log.LogDetail;

/**
 * @author walker
 * 消息处理
 */
public class MessgeDetail {

    public static void receiver() {
        LogDetail.V();
    }

    public static void recvNetworkMsg () {
        LogDetail.Debug();
    }

    public static void sMerge () {
        System.out.println("merge后，两个Branch内容相同，指向同一个commit");
    }


    public static void network () {
        System.out.println("功能3，添加");
        System.out.println("功能3，添加");
        System.out.println("功能3，添加");
        System.out.println("功能3，添加");
        System.out.println("功能3，添加");
    }
}
