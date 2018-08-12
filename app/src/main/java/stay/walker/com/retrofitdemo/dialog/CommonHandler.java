package stay.walker.com.retrofitdemo.dialog;


import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.socks.library.KLog;

import java.lang.ref.WeakReference;

/**
 * @author walker
 */
public class CommonHandler extends Handler {

    private WeakReference<DialogActivity> weakReference;

    public CommonHandler(DialogActivity activity) {
        weakReference = new WeakReference(activity);
    }

    @Override
    public void handleMessage(Message msg) {

        KLog.e("isFinishing " + weakReference.get().isFinishing());
        KLog.e(weakReference.get());
        if (weakReference.get().isFinishing()) {
            Log.e("TAG", "null - " + weakReference.get());
            return;
        }

        weakReference.get().handlerMsg(msg);


    }
}
