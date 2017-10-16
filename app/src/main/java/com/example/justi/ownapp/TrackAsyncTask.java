package com.example.justi.ownapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by justi on 15-10-2017.
 */

public class TrackAsyncTask extends AsyncTask<String, Integer, String> {
    Context context;
    MainActivity mainAct;
    FoundLyrics[] trackData;

    public TrackAsyncTask(MainActivity main){
        this.mainAct = main;
        this.context = this.mainAct.getApplicationContext();
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "searching for lyrics...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.length() == 0) {
            Toast.makeText(context, "Error: No data from server", Toast.LENGTH_SHORT).show();
        }
//        else {
//            try {
//                JSONObject trackStreamObj = new JSONObject(result);
//                JSONObject resultObj = trackStreamObj.getJSONObject("results");
//                JSONObject trackMatches = resultObj.getJSONObject("trackmatches");
//                JSONArray tracksObj = trackMatches.getJSONArray("track");
//
//                trackData = new FoundLyrics[tracksObj.length()];
//
//                // get the track, artist and image url from all the search results
//                for(int i = 0; i < tracksObj.length(); i++) {
//                    JSONObject track = tracksObj.getJSONObject(i);
//                    String full_title = track.getString("full_title");
//;
//                    trackData[i] = new FoundLyrics(full_title);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            this.mainAct.trackStartIntent(trackData);
//        }
    }
}

