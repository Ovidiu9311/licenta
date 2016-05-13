package com.example.ovidiu.licentab.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import android.util.Log;

import com.example.ovidiu.licentab.repository.*;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class ServiceRun extends Service {

    Timer timer = new Timer();
    MyTimerTask timerTask;
    ResultReceiver resultReceiver;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("start", "start");
        resultReceiver = intent.getParcelableExtra("receiver");

        timerTask = new MyTimerTask();
        timer.scheduleAtFixedRate(timerTask, 1000, 8000);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("stop", "stop");
        timer.cancel();
        Bundle bundle = new Bundle();
        bundle.putString("end", "Timer Stopped....");
        resultReceiver.send(200, bundle);

    }

    class MyTimerTask extends TimerTask {
        public MyTimerTask() {
            Bundle bundle = new Bundle();
            bundle.putString("start", "Timer Started....");
            resultReceiver.send(100, bundle);


        }

        @Override
        public void run() {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("s");
//            resultReceiver.send(Integer.parseInt(dateFormat.format(System.currentTimeMillis())), null);


                Log.i("IAUDATE", "IAUDATE");
                //nu mai fac request decomdata
                GetData data = (GetData) new GetData().execute();
                try {
                    String rezultat = data.get();
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("data", rezultat);
                    resultReceiver.send(0, bundle2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }


    }
}
