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

public class WholeLyricActivity extends AppCompatActivity {

    private FirebaseAuth authTest;
    private FirebaseAuth.AuthStateListener authStateListenerTest;
    private static final String TAG = "firebase_test";

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

        setListener();

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

