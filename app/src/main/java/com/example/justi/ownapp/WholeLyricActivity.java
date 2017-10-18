package com.example.justi.ownapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class WholeLyricActivity extends AppCompatActivity {

    String artist;
    String song;

    TextView artistText;
    TextView songText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_lyric);
        Intent intent = this.getIntent();

        artist = intent.getStringExtra("artist");
        song = intent.getStringExtra("song");

        artistText = (TextView)findViewById(R.id.textartist);
        songText = (TextView)findViewById(R.id.textsong);

        songText.setText("Song: " + song);
        artistText.setText("Artist: " + artist);
    }

    public void saveSong() {
        Intent intent = new Intent(this, RememberedActivity.class);
        intent.putExtra("artist", artist);
        intent.putExtra("song", song);
        startActivity(intent);
    }
}

