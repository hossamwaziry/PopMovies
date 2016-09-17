package com.hossam.popularmovies.HttpConnection;


import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ConnectionHttp extends AsyncTask<String, String, String> {
    HttpURLConnection httpURLConnection = null;
    BufferedReader reader = null;
    String result;

    @Override
    protected String doInBackground(String... params) {
        StringBuffer buffer = new StringBuffer();
        URL url;

        try {

            url = new URL(params[0]);
            try {

                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }
                result = buffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return result;

    }

}