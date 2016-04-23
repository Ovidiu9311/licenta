package com.example.ovidiu.licentab.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.ovidiu.licentab.activity.MainActivity;
import com.example.ovidiu.licentab.repository.GetData;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ovidiu on 4/16/2016.
 */
public class ScheduledService extends Service
{
  //  public ScheduledService() {
    //    super("ScheduledService");
  //  }//
    private Timer timer = new Timer();


//    @Override
//    public IBinder onBind(Intent intent)
//    {
//        return null;
//    }

//    @Override
//    public void onCreate()
//    {
//        super.onCreate();
//        timer.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                GetData data = (GetData) new GetData().execute();
//            }
//        }, 0, 1*30*1000);//30 secunde
//    }
//    @Override
//    protected void onHandleIntent(Intent intent) {
//        // Normally we would do some work here, like download a file.
//        // For our sample, we just sleep for 5 seconds.
//
//            timer.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    GetData data = (GetData) new GetData().execute();
//                }
//            }, 0, 1*30*1000);//30 secunde
//
//    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
//
//    @Override
//    public void onDestroy()
//    {
//        super.onDestroy();
//    }

}