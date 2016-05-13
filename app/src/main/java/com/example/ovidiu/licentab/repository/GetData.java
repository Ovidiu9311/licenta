package com.example.ovidiu.licentab.repository;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ovidiu on 4/16/2016.
 */
public  class GetData extends AsyncTask<Void, Void, String> {

    @Override
    public String doInBackground(Void... params) {

        URL url = null;
        String id ="";
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("http://10.0.2.2:8080/getMeteo");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(isw);
            String result = bufferedReader.readLine();
            id = result;
//            JSONArray jsonArray = new JSONArray(result);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject c = jsonArray.getJSONObject(i);
//
                //id = c.getString("temperatura");

//                    if(!id2.equals(id) && b) {
//                        id2 = id;
//                        b = false;
//                    }
               // System.out.println(id);


           // }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return id;

    }
}

