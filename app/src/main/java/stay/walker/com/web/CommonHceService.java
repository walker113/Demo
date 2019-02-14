package stay.walker.com.web;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.cardemulation.HostApduService;
import android.os.Bundle;


/**
 * @author 009632
 */
@SuppressLint("NewApi")
public class CommonHceService extends HostApduService {
    @Override
    public void onCreate() {
        super.onCreate();

    }
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public byte[] processCommandApdu(byte[] commandApdu, Bundle bundle) {
        return null;
    }

    @Override
    public void onDeactivated(int reason) {
    }

}
