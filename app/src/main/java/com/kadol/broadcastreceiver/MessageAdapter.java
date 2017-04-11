package com.kadol.broadcastreceiver;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nafi on 4/12/2017.
 */

public class MessageAdapter extends ArrayAdapter<String> {

    public MessageAdapter(@NonNull Context context, ArrayList<String> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listView=convertView;

        if(listView==null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        String s=getItem(position);

        TextView textView=(TextView) listView.findViewById(R.id.msg);
        textView.setText(s);

        return listView;
    }
}
