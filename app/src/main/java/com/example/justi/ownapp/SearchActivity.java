package com.example.justi.ownapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {


    Button btn;
    EditText textEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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

        Toast.makeText(SearchActivity.this, "logged Out",
                Toast.LENGTH_SHORT).show();
        finish();
    }
}
