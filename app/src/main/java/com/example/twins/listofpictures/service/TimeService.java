package com.example.twins.listofpictures.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.twins.listofpictures.MainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {
    public final static String PARAM_STATUS = "status";
    public final static String PARAM_TIME = "task";
    public final static int STATUS_START = 100;
    private Timer timer;
    private TimerTask tTask;
    private long interval = 2*60*10000;

    public TimeService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        schedule();
    }
    void schedule() {
        if (tTask != null) tTask.cancel();
        if (interval > 0) {
            tTask = new TimerTask() {
                public void run() {
                    runTask();
                }
            };
            timer.schedule(tTask, interval, interval);
        }
    }

    @Override
    public void onDestroy() {
        tTask.cancel();
        tTask = null;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void runTask(){
        Intent intent = new Intent(MainActivity.BROADCAST_ACTION);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        // send message
        intent.putExtra(PARAM_TIME,  simpleDateFormat.format(new Date()));
        intent.putExtra(PARAM_STATUS, STATUS_START);
        sendBroadcast(intent);
    }
}
