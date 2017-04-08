package com.kadol.broadcastreceiver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

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
        }



    }
}
