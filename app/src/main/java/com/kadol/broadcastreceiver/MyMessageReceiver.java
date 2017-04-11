package com.kadol.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Nafi on 4/8/2017.
 */

public class MyMessageReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle extras=intent.getExtras();

        if(extras!=null){
            Object[] pdusObj=(Object[]) extras.get("pdus");

            for(int i=0; i<pdusObj.length;i++){
                //SmsMessage currentMessage=SmsMessage.createFromPdu((byte[])pdusObj[i]);
                SmsMessage currentMessage=SmsMessage.createFromPdu((byte[])pdusObj[i]);
                String phoneNumber=currentMessage.getDisplayOriginatingAddress();
                String message=currentMessage.getMessageBody();
                Long time=currentMessage.getTimestampMillis();

                String msg=phoneNumber+": "+message;

                Log.v("watch for me: ",msg);

                Intent intent1 = new Intent(context, MainActivity.class);
                intent1.putExtra("msgContent", msg);
                context.startActivity(intent1);
            }
        }

    }
}
