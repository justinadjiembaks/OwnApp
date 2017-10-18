package com.example.justi.ownapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RememberedActivity extends AppCompatActivity {
    FoundLyrics [] favouritesArray;
    ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remembered);

        // Initialize views
        listView1 = (ListView) findViewById(R.id.list_view1);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        favouritesArray = (FoundLyrics[]) bundle.getSerializable("data");
    }


    public void makeTrackAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, fulltitleArray);

        listView1 = (ListView) findViewById(R.id.list_view);
        assert listView1 != null;
        listView1.setAdapter(arrayAdapter);
    }
}
