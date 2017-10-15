package com.example.justi.ownapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn;
        final EditText textEt;

        btn = (Button) findViewById(R.id.Button1);
        textEt = (EditText) findViewById(R.id.Edittext1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (textEt.getText().toString().equals("")){
                    Toast.makeText(getBaseContext(),"Please insert at least one character.",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), FoundItemsActivity.class);
                    intent.putExtra("lyric",textEt.getText().toString());
                    startActivity(intent);
                }

            }
        });
    }

    // pressing back button closes app
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
