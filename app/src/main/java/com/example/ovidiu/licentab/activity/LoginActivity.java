package com.example.ovidiu.licentab.activity;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ovidiu.licentab.R;

import org.apache.http.NameValuePair;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

public class LoginActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // trebuie pus altfel  textView e null
       // setContentView(R.layout.activity_login);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        TextView textView = (TextView) findViewById(R.id.textView6);
        SharedPreferences sharedpreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        String nume = (sharedpreferences.getString("Nume", null));
        textView.setText(nume);
        new LoginService().execute();
    }




    public class LoginService extends AsyncTask<Void, Void, Void> {

        @Override
        public Void doInBackground(Void... params){
            URL url = null;

            HttpURLConnection conn = null;
            try {
                url = new URL("http://10.0.2.2:8080/login");
//                urlConnection = (HttpURLConnection) url.openConnection();
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setDoInput(true);
//                List<NameValuePair>  param = new ArrayList<NameValuePair>();
//                param.add(new BasicNameValuePair("nume","Viorel"));
//                param.add( new BasicNameValuePair("password","parola"));
//                OutputStream os = urlConnection.getOutputStream();
//
//               // InputStream in = urlConnection.getInputStream();
//               // InputStreamReader isw = new InputStreamReader(in);
//               // BufferedReader bufferedReader = new BufferedReader(isw);
//                //String result = bufferedReader.readLine();
//                //JSONArray jsonArray = new JSONArray(result);
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getQuery(param));
//                writer.flush();
//                writer.close();
//                os.close();
//           url=new URL(“http://somesite/somefile.php”);

//you need to encode ONLY the values of the parameters
                String param="param1=" + URLEncoder.encode("value1","UTF-8")+
                "&param2="+URLEncoder.encode("value2","UTF-8");

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
                String fg = inStream.nextLine();
//Create JSONObject here


            }
            catch(Exception ex){

            }
            return null;
        }
        private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException
        {
            StringBuilder result = new StringBuilder();
            boolean first = true;

            for (NameValuePair pair : params)
            {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            }

            return result.toString();
        }

    }


}
