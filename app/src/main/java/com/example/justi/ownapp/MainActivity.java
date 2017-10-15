package com.example.justi.ownapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


//ShmRbvSJj6rAfxSb5K1bd5HLvJv5sbTxlmE8J3h7fByK9mfJ2u4kJ8SPdV5vMVJ7 CLIENT
//xLL8ulVXRA-c6FSgMJhlLhCt1adiUtOwHxrkkq2X8v1MUs65HzFcV6_tlVNwKxKddSsJy7IhoiVlo5rA_r75YA KEY
//hP9UVyGHrlKyRJigLqmxWNnjJSnYIbXPJvem609pzv83VRtY4PUQ-kiRD2hkUao1 Acces token
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn;
        EditText textEt;

        btn = (Button) findViewById(R.id.Button1);
        textEt = (EditText) findViewById(R.id.Edittext1);

        btn.setOnClickListener(View view);
    }
}
