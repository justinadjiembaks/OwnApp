package com.example.justi.ownapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by justi on 18-10-2017.
 * Activity for registering new users.
 */
public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Signed in", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Signed out", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    public void goToLogIn(View view){
        Intent logInIntent = new Intent(this,LogInActivity.class);
        startActivity(logInIntent);
        finish();
    }

    public void goToLogIn1(){
        Intent logInIntent = new Intent(this,LogInActivity.class);
        startActivity(logInIntent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void createUser(View view){

        EditText etEmail = (EditText) findViewById(R.id.editemail);
        EditText etPassword = (EditText)findViewById(R.id.editpassword);
        email = etEmail.getText().toString();
        password = etPassword.getText().toString();

        if (password.length()<6){
            Toast.makeText(MainActivity.this, "Password must be longer than 5 characters",
                    Toast.LENGTH_SHORT).show();
        }else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Create user", "createUserWithEmail:onComplete:" + task.isSuccessful());


                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Created New User Failed",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(MainActivity.this, "Created user " + email,
                                        Toast.LENGTH_SHORT).show();
                                goToLogIn1();

                            }

                            // ...
                        }
                    });
        }

    }
}
