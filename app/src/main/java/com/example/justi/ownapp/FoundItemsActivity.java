package com.example.justi.ownapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FoundItemsActivity extends AppCompatActivity {
    FoundLyrics [] songArray;
    ListView listView;
    ArrayList<String> fulltitleArray = new ArrayList<String>();
    ArrayList<String> nameArray = new ArrayList<String>();
    ArrayList<String> titleArray = new ArrayList<String>();


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
            fulltitleArray.add(lyrics.getFull_title());
            nameArray.add(lyrics.getTheName());
            titleArray.add(lyrics.getTheTitle());
        }

        makeTrackAdapter();

    }

    public void makeTrackAdapter(){
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,android.R.id.text1, fulltitleArray);

        listView = (ListView) findViewById(R.id.list_view);
        assert listView != null;
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Receive the strings at the clicked position
                String lv = listView.getItemAtPosition(position).toString();
                String nameartist = nameArray.get(position);
                String titlesong = titleArray.get(position);


                // Launching new Activity
                Intent i = new Intent(getApplicationContext(),WholeLyricActivity.class);

                // Sending data to new Activity
                i.putExtra("listview", lv);
                i.putExtra("artist", nameartist);
                i.putExtra("song", titlesong);
                startActivity(i);
            }
        });

    }
}
