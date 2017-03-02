package com.example.frcscoutingapp2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
{
    Spinner spinner;
    EditText emailEditText, passwordEditText, teamNumberEditText;
    Button signInButton, registerButton;
    public static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    public static int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        database.getInstance().setPersistenceEnabled(true);
        myRef = database.getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                    //get team number
                    int teamNumber = 0;
                    intent.putExtra("Team Number", teamNumber);

                    startActivity(intent);

                } else {
                    // User is signed out
                }
                // ...
            }
        };

        setContentView(R.layout.activity_main);



        emailEditText = (EditText) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.password);
        teamNumberEditText = (EditText) findViewById(R.id.teamNumber);
        signInButton = (Button) findViewById(R.id.signInButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        signInButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                signIn();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                registerUser();
            }
        });

    }


    public void signIn()
    {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        //get team number post request
        String number = teamNumberEditText.getText().toString();
        if(!email.equals("") && !password.equals(""))
        {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);

                                //get team number
                                int teamNumber = 0;

                     /*           myRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                    }

                                    @Override
                                    public void onCancelled(DatabaseError error) {
                                        // Failed to read value
                                    }
                                });*/




                                intent.putExtra("Team Number", teamNumber);
                                startActivity(intent);
                            }
                        }
                    });
        }
        else
            Toast.makeText(getApplicationContext(), "Fill in required fields.", Toast.LENGTH_SHORT).show();
    }

    public void registerUser()
    {
        final String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String number = teamNumberEditText.getText().toString();
        if(!number.equals("") && !email.equals("") && !password.equals(""))
        {
            final int teamNumber = Integer.parseInt(teamNumberEditText.getText().toString());

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (!task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            } else
                            {
                                //save user's team and launch
                                //Can't use email as identifier because of special characters
                                String encodedEmail = email.replace('.',',');
                                encodedEmail = encodedEmail.replace('$',',');
                                encodedEmail = encodedEmail.replace('[',',');
                                encodedEmail = encodedEmail.replace(']',',');
                                encodedEmail = encodedEmail.replace('/',',');
                                encodedEmail = encodedEmail.replace('#',',');
                                myRef.child(encodedEmail).setValue(teamNumber);
                                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                                intent.putExtra("Team Number", teamNumber);
                                startActivity(intent);
                            }
                        }
                    });
        }
        else
            Toast.makeText(getApplicationContext(), "Fill in required fields.", Toast.LENGTH_SHORT).show();
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
}
