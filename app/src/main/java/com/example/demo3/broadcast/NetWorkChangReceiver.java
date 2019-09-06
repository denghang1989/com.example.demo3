package com.example.demo3.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.demo3.event.NetConnectChangedEvent;
import com.example.demo3.event.WifiChangedEvent;

import org.greenrobot.eventbus.EventBus;

public class NetWorkChangReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                case WifiManager.WIFI_STATE_DISABLING:
                    EventBus.getDefault().post(new WifiChangedEvent(false));
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                case WifiManager.WIFI_STATE_ENABLING:
                    EventBus.getDefault().post(new WifiChangedEvent(true));
                    break;
            }
        }

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            if (NetworkUtils.isConnected()) {
                EventBus.getDefault().post(new NetConnectChangedEvent(true));
            } else {
                EventBus.getDefault().post(new NetConnectChangedEvent(false));
            }
        }
    }

}
