package com.kadol.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by Nafi on 4/8/2017.
 */

public class MyPhoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras=intent.getExtras();
        if(extras!=null){
            String state=extras.getString(TelephonyManager.EXTRA_STATE);
            Log.v("Watch for me: ",state);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String phoneNumber=extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                Log.v("Watch for me: ",phoneNumber);
            }
        }
    }
}
