package com.example.justi.ownapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by justi on 18-10-2017.
 * Activity where the found songs are stored in an array and shown
 * to the user.
 */

public class FoundItemsActivity extends AppCompatActivity {

    private FirebaseAuth authTest;
    private FirebaseAuth.AuthStateListener authStateListenerTest;
    private static final String TAG = "firebase_test";

    FoundLyrics [] songArray;
    ListView listView;
    ArrayList<String> fullTitleArray = new ArrayList<String>();
    ArrayList<String> nameArray = new ArrayList<String>();
    ArrayList<String> titleArray = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_items);

        // Initialize views
        listView = (ListView) findViewById(R.id.list_view);

        setListener();

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        songArray = (FoundLyrics[]) bundle.getSerializable("data");

        for (FoundLyrics lyrics : songArray) {
            fullTitleArray.add(lyrics.getFull_title());
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

    public void setListener(){

        authStateListenerTest = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    //User is singed in
                    Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
                }else{
                    // User is signet out
                    Log.d(TAG,"onAuthStateChanged:signed_out");
                    goToMain();
                }
            }
        };
    }

    public void goToMain(){
        Intent Main = new Intent(this, MainActivity.class);
        startActivity(Main);
        finish();
    }

}
