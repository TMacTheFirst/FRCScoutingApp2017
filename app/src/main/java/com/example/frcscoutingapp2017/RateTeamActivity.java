package com.example.frcscoutingapp2017;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RateTeamActivity extends AppCompatActivity
{
    Spinner spinner;
    Button saveTeamButton;
    EditText teamNumberEditText;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //The value of myRef is the head of the table.
    //The .child(id) is a way to make arrays
    DatabaseReference myRef = database.getReference("2890");
    DatabaseReference myRef2 = database.getReference("Test");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_scouting_page);

        teamNumberEditText = (EditText) findViewById(R.id.teamNumberEditText);
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

    //Save a team to google firebase instance
    public void saveTeam()
    {
        int id = MainActivity.id;
        String num = teamNumberEditText.getText().toString();
        int teamNumber = 0;
        if(!num.equals(""))
            teamNumber = Integer.parseInt(num);
        String teamName = "";
        boolean highGoal = false, lowGoal = false, gears = false, ropeLift = false;
        boolean leftGear = false, middleGear = false, rightGear = false, highGoalAuto = false, lowGoalAuto = false, crossLine = false;
        String rating = "", notes = "";
        TeamRating team = new TeamRating(teamNumber, "test", highGoal, lowGoal, gears, ropeLift,
                leftGear, middleGear, rightGear, highGoalAuto, lowGoalAuto, crossLine, rating, notes);

        //write team to database
//        myRef.setValue(team);

        myRef.child(""+teamNumber).setValue(team);
     //   myRef2.child(""+id).setValue(team);
        MainActivity.id ++;
    }
}
