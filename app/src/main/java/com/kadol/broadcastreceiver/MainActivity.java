package com.kadol.broadcastreceiver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static String READ_PHONE_STATE= Manifest.permission.READ_PHONE_STATE;

    private static String RECEIVE_SMS=Manifest.permission.RECEIVE_SMS;

    ArrayList<String> arrayList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(getApplicationContext(),READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{READ_PHONE_STATE,RECEIVE_SMS},123);
        }else {

            readSms();
        }

        ListView listView=(ListView) findViewById(R.id.list);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String messageContent = extras.getString("msgContent");
            arrayList.add(0,messageContent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case 123:{
                if(grantResults.length>0 && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    readSms();
                }
            }
        }
    }

    private void readSms() {

        Cursor cursor=getContentResolver().query(Uri.parse("content://sms/inbox"),null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                String msg;
                String phoneNumber=cursor.getString(2);

                if(phoneNumber.equals("123456")){

                    String body=cursor.getString(12);
                    String date=getTimeFormatDone(cursor.getLong(4));

                    msg=phoneNumber+": "+body+" "+date;
                    Log.v("Watch for me: ",msg);
                    arrayList.add(msg);
                }

            }while(cursor.moveToNext());
        }

    }

    private String getTimeFormatDone(long time) {

        Date date = new Date(time);

        java.text.SimpleDateFormat dateFormat=new java.text.SimpleDateFormat("MMM d, h:mm:ss a");
        return dateFormat.format(date);
    }
}
