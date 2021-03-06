package com.example.justi.ownapp;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by justi on 18-10-2017.
 * This Activity shows the user information about the song. The intention was to show the
 * full lyrics here, but this JSON string was only provided in the paid version of the API :(.
 */
public class WholeLyricActivity extends AppCompatActivity {

    // enables the use of Firebase
    private FirebaseAuth authTest;
    private FirebaseAuth.AuthStateListener authStateListenerTest;
    private static final String TAG = "firebase_test";
    private DatabaseReference mDatabase;

    String artist;
    String song;
    int number;

    TextView artistText;
    TextView songText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whole_lyric);
        Intent intent = this.getIntent();

        setListener();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        getFromDB();

        artist = intent.getStringExtra("artist");
        song = intent.getStringExtra("song");

        artistText = (TextView) findViewById(R.id.textartist);
        songText = (TextView) findViewById(R.id.textsong);

        songText.setText("Song: " + song);
        artistText.setText("Artist: " + artist);
        btn = (Button) findViewById(R.id.savebutton);


    }
    // When return button is pressed, go to last Activity
        public void back(View v){

            onBackPressed();
    }
    // when add to favourites is pressed
        public void saveSong(View v){
            // Add to database
            FavouriteSongs favouriteSongs = new FavouriteSongs(number,artist,song);
            Counter newCounter = new Counter(number);
            String numberText = String.valueOf(newCounter.counter);
            mDatabase.child("All").child(numberText).setValue(favouriteSongs);
            mDatabase.child("Here").setValue(newCounter.counter);

            // start RememberActivity
            Intent saveIntent = new Intent(getApplicationContext(),RememberedActivity.class);
            startActivity(saveIntent);
        }

    // when Log out is pressed , log out and go to LogInActivity
    public void logOut1(View view){
        Intent logoutIntent = new Intent(this,LogInActivity.class);
        startActivity(logoutIntent);
        Toast.makeText(WholeLyricActivity.this, "Logged Out",
                Toast.LENGTH_SHORT).show();
        finish();
    }

    // checks is user is logged in
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

                    // when isn't logged in go to LogInActivity automatically
                    goToLogin();
                }
            }
        };
    }

    // goes to LogInActivity
    public void goToLogin(){
        Intent Login= new Intent(this, LogInActivity.class);
        startActivity(Login);
        finish();
    }

    // gets data from database
    public void getFromDB(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get our object out of the database
                Counter getCounter = dataSnapshot.child("Here").getValue(Counter.class);

                // gets amount of favourite items
                number = getCounter.counter;

                // sets new number ready for new favourite song input
                number += 1;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Getting the data failed, log a message
                Log.w(TAG,"Failed to get data from Database", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(postListener);
    }

}

