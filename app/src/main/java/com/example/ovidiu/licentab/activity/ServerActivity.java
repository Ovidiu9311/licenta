package com.example.ovidiu.licentab.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ovidiu.licentab.R;
import com.example.ovidiu.licentab.service.Server;

public class ServerActivity extends Activity {

    boolean mBounded;
    Server mServer;
    TextView text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);

        text = (TextView)findViewById(R.id.textView11);
        button = (Button) findViewById(R.id.button6);
        //mServer.getTime(TextView text);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                text.setText(mServer.getTime());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent mIntent = new Intent(this, Server.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    };

    ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(ServerActivity.this, "Service is disconnected",Toast.LENGTH_SHORT).show();
            mBounded = false;
            mServer = null;
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(ServerActivity.this, "Service is connected", Toast.LENGTH_SHORT).show();
            mBounded = true;
            Server.LocalBinder mLocalBinder = (Server.LocalBinder)service;
            mServer = mLocalBinder.getServerInstance();
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        if(mBounded) {
            unbindService(mConnection);
            mBounded = false;
        }
    };
}
