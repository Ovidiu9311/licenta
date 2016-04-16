package com.example.ovidiu.licentab.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.ovidiu.licentab.activity.MainActivity;
import com.example.ovidiu.licentab.repository.GetData;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ovidiu on 4/16/2016.
 */
public class ScheduledService extends Service
{

    private Timer timer = new Timer();


    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                GetData data = (GetData) new GetData().execute();
            }
        }, 0, 1*30*1000);//30 secunde
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

}