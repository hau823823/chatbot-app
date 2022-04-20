package com.example.apptest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class urlConnect extends Thread {
    private boolean lock;
    private String urlStr;
    private String connectionResult;
    private HttpURLConnection urlConnection;

    public urlConnect(String url) {
        lock = true;
        urlStr = url;
        connectionResult = "";
    }

    public void run() {
        try {
            URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setInstanceFollowRedirects(true);
            // if HTTP return 200 (OK)
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String tempStr;
                StringBuffer sb = new StringBuffer();
                while ((tempStr = br.readLine()) != null) {
                    sb.append(tempStr);
                }
                br.close();
                is.close();
                connectionResult = sb.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            lock = false;
        }
    }

    public boolean getLockStatus() {
        return lock;
    }

    public String getResult() {
        return connectionResult;
    }
}
