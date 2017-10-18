package com.example.justi.ownapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WholeLyricActivity extends AppCompatActivity {

    String full_name;
    String lyrics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_lyric);
        Intent intent = this.getIntent();

        full_name = intent.getStringExtra("listview");
        lyrics = intent.getStringExtra("lyrics;");
}
