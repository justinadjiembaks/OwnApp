package com.example.justi.ownapp;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WholeLyricActivity extends AppCompatActivity {

    String artist;
    String song;

    TextView artistText;
    TextView songText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_lyric);
        Intent intent = this.getIntent();

        artist = intent.getStringExtra("artist");
        song = intent.getStringExtra("song");

        artistText = (TextView) findViewById(R.id.textartist);
        songText = (TextView) findViewById(R.id.textsong);

        songText.setText("Song: " + song);
        artistText.setText("Artist: " + artist);
        btn = (Button) findViewById(R.id.savebutton);
    }

        public void back(View v){
            onBackPressed();
    }
        public void savesong(View v){

            Intent saveintent = new Intent(getApplicationContext(),RememberedActivity.class);
            saveintent.putExtra("artist", artist);
            saveintent.putExtra("song", song);
            startActivity(saveintent);
            finish();
        }

    public void logOut1(View view){
        Intent logoutIntent = new Intent(this,MainActivity.class);
        startActivity(logoutIntent);
        Toast.makeText(WholeLyricActivity.this, "Logged Out",
                Toast.LENGTH_SHORT).show();
        finish();
    }

}

