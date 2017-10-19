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
    public void logIn(View view){

        EditText et1mail = (EditText) findViewById(R.id.editemail1);
        EditText et1password= (EditText)findViewById(R.id.editpassword1);
        email1 = et1mail.getText().toString();
        password1 = et1password.getText().toString();

        if (email1.isEmpty() || password1.isEmpty()){
            Toast.makeText(LogInActivity.this, "Email and/or Password is/are not filled in" ,
                    Toast.LENGTH_LONG).show();
        }else {

            mAuth.signInWithEmailAndPassword(email1, password1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("Succesful", "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w("Logging in Failed", "signInWithEmail:failed", task.getException());
                                Toast.makeText(LogInActivity.this, "Failed",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LogInActivity.this, "Succesfuly logged in as " + email1,
                                        Toast.LENGTH_SHORT).show();
                                searchscreen();
                            }

                            // ...
                        }
                    });
        }
    }
    public void searchscreen(){

        Intent search = new Intent(this, SearchActivity.class);
        startActivity(search);
        finish();
    }

    public void goToRegister(View view){
        Intent gotoregisterIntent = new Intent(this, MainActivity.class);
        startActivity(gotoregisterIntent);
        finish();
    }
}
