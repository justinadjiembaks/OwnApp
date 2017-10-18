package com.example.justi.ownapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FoundItemsActivity extends AppCompatActivity {
    FoundLyrics [] songArray;
    ListView listView;
    ArrayList<String> trackArray = new ArrayList<String>();
    ArrayList<String> lyricsArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_items);

        // Initialize views
        listView = (ListView) findViewById(R.id.list_view);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        songArray = (FoundLyrics[]) bundle.getSerializable("data");

        for (FoundLyrics lyrics : songArray) {
            trackArray.add(lyrics.getFull_title());
            lyricsArray.add(lyrics.getlyrics());
        }

        makeTrackAdapter();

    }

    public void makeTrackAdapter(){
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,android.R.id.text1,trackArray);

        listView = (ListView) findViewById(R.id.list_view);
        assert listView != null;
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Receive the strings at the clicked position
                String lv = listView.getItemAtPosition(position).toString();

                // Launching new Activity
                Intent i = new Intent(getApplicationContext(),WholeLyricActivity.class);

                // Sending data to new Activity
                i.putExtra("listview", lv);
                i.putExtra("lyrics", lyrics);
                startActivity(i);
            }
        });

    }
}
