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
}
