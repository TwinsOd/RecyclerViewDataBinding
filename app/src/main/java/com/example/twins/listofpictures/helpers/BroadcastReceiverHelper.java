package com.example.twins.listofpictures.helpers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.twins.listofpictures.MainActivity;
import com.example.twins.listofpictures.receiver.TimeBroadcastReceiver;
import com.example.twins.listofpictures.service.TimeService;

/**
 * Created by Twins on 09.09.2016.
 */

public class BroadcastReceiverHelper {
    private TimeBroadcastReceiver timeBroadcastReceiver = null;
    private Context context;

    public void start(Context context) {
        this.context = context;

        if (timeBroadcastReceiver == null)
            timeBroadcastReceiver = new TimeBroadcastReceiver();

        // create filter for BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(MainActivity.BROADCAST_ACTION);

        context.registerReceiver(timeBroadcastReceiver, intentFilter);
        context.startService(new Intent(context, TimeService.class));
    }

    public void stop() {
        context.unregisterReceiver(timeBroadcastReceiver);
        timeBroadcastReceiver = null;
    }
}
