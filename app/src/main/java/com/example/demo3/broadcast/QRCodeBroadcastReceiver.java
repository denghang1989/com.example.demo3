package com.example.demo3.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.demo3.event.QRCodeEvent;

import org.greenrobot.eventbus.EventBus;

public class QRCodeBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.scanner.broadcast".equals(action)) {
            String value = intent.getStringExtra("data");
            EventBus.getDefault().post(new QRCodeEvent(value));
        }

    }

}
