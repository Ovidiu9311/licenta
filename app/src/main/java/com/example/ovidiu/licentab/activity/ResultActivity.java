package com.example.ovidiu.licentab.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ovidiu.licentab.R;
import com.example.ovidiu.licentab.service.ServiceRun;

public class ResultActivity extends Activity {


    Intent intent;
    TextView txtview;
    MyResultReceiver resultReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultReceiver = new MyResultReceiver(null);
        txtview = (TextView) findViewById(R.id.textView12);
        intent = new Intent(this, ServiceRun.class);
        intent.putExtra("receiver", resultReceiver);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intent);
    }


    class UpdateUI implements Runnable
    {
        String updateString;

        public UpdateUI(String updateString) {
            this.updateString = updateString;
        }
        public void run() {
            txtview.setText(updateString);
        }
    }

    class MyResultReceiver extends ResultReceiver
    {
        public MyResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            if(resultCode == 100){
                runOnUiThread(new UpdateUI(resultData.getString("start")));
            }
            else if(resultCode == 200){
                runOnUiThread(new UpdateUI(resultData.getString("end")));
            }
            else{
                runOnUiThread(new UpdateUI("Result Received "+resultData.getString("data")));
            }
        }
    }
}
