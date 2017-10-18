package com.example.justi.ownapp;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SearchActivity extends AppCompatActivity {

    private FirebaseAuth authTest;
    private FirebaseAuth.AuthStateListener authStateListenerTest;
    private static final String TAG = "firebase_test";
    Button btn;
    EditText textEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        authTest = FirebaseAuth.getInstance();
        setListener();

        btn = (Button) findViewById(R.id.Button1);
        textEt = (EditText) findViewById(R.id.Edittext1);
    }

    // pressing back button closes app
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void favourites(View view) {
        Intent favouritesintent = new Intent(this, RememberedActivity.class);
        startActivity(favouritesintent);
    }

    public void trackSearch(View view) {
        String trackSearch = textEt.getText().toString();

        if (trackSearch.equals("")) {
            Toast.makeText(this.getApplicationContext(), "Please insert at least one character",
                    Toast.LENGTH_SHORT).show();

        } else {
            TrackAsyncTask asyncTask = new TrackAsyncTask(this);
            asyncTask.execute(trackSearch);
            textEt.getText().clear();
        }
    }

    public void trackStartIntent(FoundLyrics[] trackData) {
        Intent dataIntent = new Intent(this, FoundItemsActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("data", trackData);
        dataIntent.putExtras(bundle);

        this.startActivity(dataIntent);
        finish();
    }

    public void logOut(View view){
        Intent logoutIntent = new Intent(this,MainActivity.class);
        startActivity(logoutIntent);

        Toast.makeText(SearchActivity.this, "Logged Out",
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
