package com.example.frcscoutingapp2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Spinner spinner;
    EditText emailEditText, passwordEditText, teamNumberEditText;
    Button signInButton, registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
        String number = teamNumberEditText.getText().toString();
        int teamNumber = 0;
        if(!number.equals(""))
        {
            teamNumber = Integer.parseInt(teamNumberEditText.getText().toString());
            if(false)
            {
                Toast.makeText(getApplicationContext(), "Username/password incorrect. Try again.", Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent intent = new Intent(this, ListViewActivity.class);
                //           intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }

        }
        else
            Toast.makeText(getApplicationContext(), "Choose a team to join.", Toast.LENGTH_LONG).show();



    }

    public void registerUser()
    {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        int teamNumber = Integer.parseInt(teamNumberEditText.getText().toString());

        if(false)
        {
            Toast.makeText(getApplicationContext(), "Username/password incorrect. Try again", Toast.LENGTH_SHORT);
        }
        else
        {
            Intent intent = new Intent(this, ListViewActivity.class);
            //           intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }

    }
}
