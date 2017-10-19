package com.example.justi.ownapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by justi on 18-10-2017.
 * Activity that allows the user to log in
 */
public class LogInActivity extends AppCompatActivity {

    String email1, password1;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Signed In", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Signed Out", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    // when log in button is pressed in..
    public void logIn(View view){

        EditText et1Email = (EditText) findViewById(R.id.editemail1);
        EditText et1Password= (EditText)findViewById(R.id.editpassword1);
        email1 = et1Email.getText().toString();
        password1 = et1Password.getText().toString();

        // check if password and email is filled in
        if (email1.isEmpty() || password1.isEmpty()){
            Toast.makeText(LogInActivity.this, "Email and/or Password is/are not filled in" ,
                    Toast.LENGTH_LONG).show();
        }else {
            // when filled in checks if password and email are correct
            mAuth.signInWithEmailAndPassword(email1, password1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Succesful", "signInWithEmail:onComplete:" + task.isSuccessful());

                            // if not succesful show error message
                            if (!task.isSuccessful()) {
                                Log.w("Logging in Failed", "signInWithEmail:failed", task.getException());
                                Toast.makeText(LogInActivity.this, "Failed",
                                        Toast.LENGTH_SHORT).show();
                            } else {

                                // if succesful show message and go to next Activity
                                Toast.makeText(LogInActivity.this, "Succesfully logged in as " + email1,
                                        Toast.LENGTH_SHORT).show();
                                searchScreen();
                            }

                            // ...
                        }
                    });
        }
    }
    // goes to searchActivity
    public void searchScreen(){

        Intent search = new Intent(this, SearchActivity.class);
        startActivity(search);
        finish();
    }

    // goes to registerActivity
    public void goToRegister(View view){
        Intent goToRegisterIntent = new Intent(this, MainActivity.class);
        startActivity(goToRegisterIntent);
        finish();
    }
}
