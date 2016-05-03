package com.example.ovidiu.licentab.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ovidiu.licentab.R;
import com.example.ovidiu.licentab.activity.MainActivity;
import com.example.ovidiu.licentab.repository.GetData;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * Created by Ovidiu on 4/16/2016.
 */
public class ScheduledService extends Service
{
  private final IBinder mBinder = new LocalBinder();
    private Timer timer = new Timer();

    public class LocalBinder extends Binder {
        public ScheduledService getService() {
            // Return this instance of LocalService so clients can call public methods
         return ScheduledService.this;
     }
    }

    @Override
    public IBinder onBind(Intent arg0) {

        Log.d("aici", "aici");
        return null;
    }


    public void getData(final TextView textView2) {
        // Let it continue running until it is stopped.
        // Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        Log.d("ServiceScheduled", "ServiceScheduled");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.i("IAUDATE", "IAUDATE");
                GetData data = (GetData) new GetData().execute();
                try {
                    String rezultat = data.get();
                    textView2.setText(rezultat);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1 * 30 * 1000);//30 secunde
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }

}