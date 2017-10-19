package com.example.justi.ownapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by justi on 15-10-2017.
 * This TrackAsyncTask retreives the string result from HttpRequestHelper
 * and strips the JSON information that the user needs
 */

public class TrackAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    SearchActivity mainAct;
    FoundLyrics[] trackData;

    public TrackAsyncTask(SearchActivity main){
        this.mainAct = main;
        this.context = this.mainAct.getApplicationContext();
    }

    // makes Toast while searching for songs
    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "searching for lyrics...", Toast.LENGTH_SHORT).show();
    }

    // ensures that app doesn't crash
    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    // strips the result string into the required data
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // checks if there is reveived data, otherwise gives error
        if (result.length() == 0) {
            Toast.makeText(context, "Error: No data from server", Toast.LENGTH_SHORT).show();
        }
        else {
            // when received data strip data
            try {
                JSONObject trackStreamObj = new JSONObject(result);
                JSONObject resultObj = trackStreamObj.getJSONObject("response");
                JSONArray trackMatches = resultObj.getJSONArray("hits");
                trackData = new FoundLyrics[trackMatches.length()];

                // get the track, artist and image url from all the search results
                for(int i = 0; i < trackMatches.length(); i++) {
                    JSONObject track = trackMatches.getJSONObject(i);
                    JSONObject lyric = track.getJSONObject("result");

                    // put full_title, name and artist in string
                    String full_title = lyric.getString("full_title");
                    String title = lyric.getString("title");
                    JSONObject primary_artist = lyric.getJSONObject("primary_artist");
                    String name = primary_artist.getString("name");

                    // put found strings in object FoundLyrics
                    trackData[i] = new FoundLyrics(full_title, name, title);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.mainAct.trackStartIntent(trackData);
        }
    }
}

