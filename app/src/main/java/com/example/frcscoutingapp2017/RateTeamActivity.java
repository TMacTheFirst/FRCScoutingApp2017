package com.example.frcscoutingapp2017;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class RateTeamActivity extends AppCompatActivity
{
    Spinner spinner;
    Button saveTeamButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_scouting_page);

        saveTeamButton = (Button) findViewById(R.id.saveTeamButton);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("No Rating");
        spinnerArray.add("Green");
        spinnerArray.add("Yellow");
        spinnerArray.add("Red");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);

        saveTeamButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                saveTeam();
            }
        });

    }

    public void saveTeam()
    {

    }
}
