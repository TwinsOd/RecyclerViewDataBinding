package com.example.twins.listofpictures.receiver;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.twins.listofpictures.TimeDialogFragment;
import com.example.twins.listofpictures.service.TimeService;

public class TimeBroadcastReceiver extends BroadcastReceiver {
    private FragmentManager fragmentManager;

    public TimeBroadcastReceiver(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int status = intent.getIntExtra(TimeService.PARAM_STATUS, 0);

        // Catch messages about start tasks
        if (status == TimeService.STATUS_START) {

            String resultTime = intent.getStringExtra(TimeService.PARAM_TIME);

            TimeDialogFragment timeDialogFragment = TimeDialogFragment.newInstance(resultTime);
            timeDialogFragment.show(fragmentManager, "time_dialog");

        }
    }
}
