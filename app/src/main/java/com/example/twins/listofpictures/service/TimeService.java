package com.example.twins.listofpictures.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.twins.listofpictures.MainActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeService extends Service {
    public final static String PARAM_STATUS = "status";
    public final static String PARAM_TIME = "task";
    public final static int STATUS_START = 100;

    public TimeService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void someTask() {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    try {
                        TimeUnit.MINUTES.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(MainActivity.BROADCAST_ACTION);

                    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Amsterdam"));
                    Date currentLocalTime = cal.getTime();
                    DateFormat date = new SimpleDateFormat("HH:mm", new Locale("nl", "nl"));
                    date.setTimeZone(TimeZone.getTimeZone("Europe/Amsterdam"));
                    String currenttime = date.format(currentLocalTime);

                    // send message
                    intent.putExtra(PARAM_TIME, currenttime);
                    intent.putExtra(PARAM_STATUS, STATUS_START);
                    sendBroadcast(intent);
                }
                stopSelf();
            }
        }
        ).start();
    }
}
