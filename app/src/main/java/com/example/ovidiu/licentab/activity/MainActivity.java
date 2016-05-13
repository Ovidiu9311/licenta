package com.example.ovidiu.licentab.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ovidiu.licentab.R;
import com.example.ovidiu.licentab.service.ScheduledService;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class MainActivity extends ActionBarActivity {


    SharedPreferences sharedpreferences;
    private GoogleApiClient client;
    ScheduledService mService;
    boolean mBound = false;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ScheduledService.LocalBinder binder = (ScheduledService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Intent intent = new Intent(this, ScheduledService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Log.d("aici", "aici");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startActivity(View view) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        startActivity(intent);
    }


    public void login(View view) {
        ///Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        Context context = MainActivity.this;
//       SharedPreferences.Editor editor = context.getSharedPreferences("Mata", Context.MODE_PRIVATE).edit();
//      editor.putString("status", "1");
//        editor.commit();
//        sharedpreferences = getSharedPreferences("Mama", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putString("Nume", "drac");
//        editor.commit();
        EditText editText = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        TextView textView = (TextView) findViewById(R.id.textView7);
        if(editText.getText().toString().equals("") || editText2.getText().toString().equals(""))
            textView.setText("Completati Nume sau Parola");
        if(!(editText.getText().toString().equals("") && editText2.getText().toString().equals(""))) {
            new GetDataUser().execute();
            //startActivity(intent);
        }

    }

    public void alertNot(View view){ // trebuie pus View view intotdeauna altfel nu merge

//        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
//        //mBuilder.setSmallIcon(R.drawable.notification_icon);
//        mBuilder.setContentTitle("Notification Alert, Click Me!");
//        mBuilder.setContentText("Hi, This is Android Notification Detail!");
//        //NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        Intent resultIntent = new Intent(this, MainActivity.class);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(MainActivity.class);
//
//// Adds the Intent that starts the Activity to the top of the stack
//        stackBuilder.addNextIntent(resultIntent);
//        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
//        mBuilder.setContentIntent(resultPendingIntent);
//        // notificationID allows you to update the notification later on.
//        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//// notificationID allows you to update the notification later on.
//        mNotificationManager.notify(9999, mBuilder.build());


        // parte notificare merge


//        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        @SuppressWarnings("deprecation")
//
//        Notification notification = new Notification(R.drawable.abc_ab_share_pack_holo_light,"New Message", System.currentTimeMillis());
//        Intent notificationIntent = new Intent(this,MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
//
//        notification.setLatestEventInfo(MainActivity.this, "Prima","Notificvasrer", pendingIntent);
//        notificationManager.notify(9999, notification);
        TextView textView = (TextView) findViewById(R.id.textView);
        mService.getData(textView);
    }





    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ovidiu.licentab/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
      //  startService(new Intent(getBaseContext(), ScheduledService.class));
        Log.d("aici", "aici");
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.ovidiu.licentab/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
        client.disconnect();
    }

    public  class GetData extends AsyncTask<Void, Void, Void> {

        String id = "";
        String id2 = "a";
        Boolean b = true;
        TextView textView = (TextView) findViewById(R.id.textView);

        @Override
        public Void doInBackground(Void... params) {

            URL url = null;

            HttpURLConnection urlConnection = null;
            try {
                url = new URL("http://10.0.2.2:8080/getMeteo");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(isw);
                String result = bufferedReader.readLine();
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);

                    id = c.getString("temperatura");

//                    if(!id2.equals(id) && b) {
//                        id2 = id;
//                        b = false;
//                    }
                    System.out.println(id);


                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void unused) {

            textView.setText(id);
        }


    }

    public class GetDataUser extends AsyncTask<String, Void, String> {

        EditText editText;
        EditText editText2;
        String username;
        String password;
        String fg;
        @Override
        public String doInBackground(String... data) {

            URL url = null;

            HttpURLConnection conn = null;
            try {
                url = new URL("http://10.0.2.2:8080/login");
                String param="param1=" + URLEncoder.encode(username, "UTF-8")+
                        "&param2="+URLEncoder.encode(password,"UTF-8");

                conn=(HttpURLConnection)url.openConnection();
//set the output to true, indicating you are outputting(uploading) POST data
                conn.setDoOutput(true);
//once you set the output to true, you don’t really need to set the request method to post, but I’m doing it anyway
                conn.setRequestMethod("POST");

//Android documentation suggested that you set the length of the data you are sending to the server, BUT
// do NOT specify this length in the header by using conn.setRequestProperty(“Content-Length”, length);
//use this instead.
                conn.setFixedLengthStreamingMode(param.getBytes().length);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//send the POST out
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(param);
                out.close();

//build the string to store the response text from the server
                String response= "";

//start listening to the stream
                Scanner inStream = new Scanner(conn.getInputStream());
                fg = inStream.nextLine();
                if(fg.equals("true")) {
                    SharedPreferences sharedpreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("Nume",username);
                    editor.commit();
//                    String channel = (sharedpreferences.getString("Nume", null));
//                    String boss = "tacto";
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);

                }

//Create JSONObject here
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null)
                    conn.disconnect();
            }
            return "";

        }



        @Override
        protected void onPostExecute(String unused) {
            if (fg.equals("false")){
                TextView textView = (TextView) findViewById(R.id.textView8);
                textView.setText("Username sau parola incorecte");
            }

        }
        @Override
        protected void onPreExecute(){
            editText = (EditText) findViewById(R.id.editText);
            editText2 = (EditText) findViewById(R.id.editText2);
            username = editText.getText().toString();
            password  = editText2.getText().toString();
        }

    }

}