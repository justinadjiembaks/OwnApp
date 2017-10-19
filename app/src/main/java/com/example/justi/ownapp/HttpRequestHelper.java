package com.example.justi.ownapp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by justi on 15-10-2017.
 *
 * Javaclass that connects the app to the internet and retreives the information
 * of the site.
 */

public class HttpRequestHelper {
    protected static synchronized String downloadFromServer(String... params) {

        String result = "";
        String chosenTag = params[0];

        String token = "hP9UVyGHrlKyRJigLqmxWNnjJSnYIbXPJvem609pzv83VRtY4PUQ-kiRD2hkUao1";
        String urlGenius = ("https://api.genius.com/search?access_token=" + token + "&q=" + chosenTag);

        URL url = null;
        try {
            url = new URL(urlGenius);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connect;

        if(url != null) {
            try {
                connect = (HttpURLConnection) url.openConnection();
                connect.setRequestMethod("GET");

                Integer responseCode = connect.getResponseCode();

                if (responseCode >= 200 && responseCode < 300) {
                    BufferedReader bReader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
                    String line;
                    while ((line = bReader.readLine()) != null) {
                        result += line;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

