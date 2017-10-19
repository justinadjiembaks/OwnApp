package com.example.justi.ownapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by justi on 18-10-2017.
 * Activity that shows the favourites songs of the user
 */
public class RememberedActivity extends AppCompatActivity {

    private FirebaseAuth authTest;
    private FirebaseAuth.AuthStateListener authStateListenerTest;
    private static final String TAG = "firebase_test";
    private DatabaseReference mDatabase;
    int number;

    ArrayList<String> favouritesArtistArray;
    ArrayList<String> favouritesSongArray;
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remembered);

        setListener();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        getFromDB();
        getFromDB2(number);

        // Initialize views
        listView1 = (ListView) findViewById(R.id.list_view1);
        favouritesArtistArray = new ArrayList<>();
        favouritesArtistArray.add("hallo");
        favouritesArtistArray.add("test");
        makeTrackAdapter();
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
                    goToLogin();
                }
            }
        };
    }

    public void goToLogin(){
        Intent Login = new Intent(this, LogInActivity.class);
        startActivity(Login);
        finish();
    }
    public void getFromDB(){

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get our object out of the database
                Counter getCounter = dataSnapshot.child("Here").getValue(Counter.class);
                number = getCounter.counter;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to get data from Database", databaseError.toException());
            }
        };

        mDatabase.addValueEventListener(postListener);
    }
    public void getFromDB2(final int var){

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (int i = 0 ;(i < var);i++) {
                    // Get our object out of the database
                    FavouriteSongs favouriteSongs = dataSnapshot.child("All").child(String.valueOf(i)).getValue(FavouriteSongs.class);
                    String artist = favouriteSongs.getTheName();
                    String song = favouriteSongs.getTheTitle();
                    favouritesArtistArray.add(artist);
                    favouritesSongArray.add(song);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to get data from Database", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(postListener);
    }
    public void makeTrackAdapter() {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, favouritesArtistArray);

        listView1 = (ListView) findViewById(R.id.list_view1);
        assert listView1 != null;
        listView1.setAdapter(arrayAdapter);
    }
}
