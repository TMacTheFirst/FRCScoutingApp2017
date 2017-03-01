package com.example.frcscoutingapp2017;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewTeamActivity extends AppCompatActivity
{
    Spinner spinner;
    Button saveTeamButton;
    EditText teamNumberEditText, teamNameEditText, notesEditText;
    CheckBox highGoal, lowGoal, gears, ropeLift;
    CheckBox leftGear, middleGear, rightGear, highGoalAuto, lowGoalAuto, crossLine;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("2890");
    static TeamRating team;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_scouting_page);

        final int teamNumber = (int) getIntent().getExtras().get("teamNumber");
        final TeamRating team;

        teamNumberEditText = (EditText) findViewById(R.id.teamNumberEditText);
        saveTeamButton = (Button) findViewById(R.id.saveTeamButton);
        spinner = (Spinner) findViewById(R.id.spinner);
        teamNameEditText = (EditText) findViewById(R.id.teamNameEditText);
        notesEditText = (EditText) findViewById(R.id.notesEditText);
        highGoal = (CheckBox) findViewById(R.id.highGoal);
        lowGoal = (CheckBox) findViewById(R.id.lowGoal);
        gears = (CheckBox) findViewById(R.id.gears);
        ropeLift = (CheckBox) findViewById(R.id.ropeLift);
        leftGear = (CheckBox) findViewById(R.id.leftGear);
        middleGear = (CheckBox) findViewById(R.id.middleGear);
        rightGear = (CheckBox) findViewById(R.id.rightGear);
        highGoalAuto = (CheckBox) findViewById(R.id.highGoalAuto);
        lowGoalAuto = (CheckBox) findViewById(R.id.lowGoalAuto);
        crossLine = (CheckBox) findViewById(R.id.crossLine);


        ArrayList<String> spinnerArray = new ArrayList<String>();
        spinnerArray.add("No Rating");
        spinnerArray.add("Green");
        spinnerArray.add("Yellow");
        spinnerArray.add("Red");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);


        //READ IN TEAM DATA AND FILL IN UI
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                TeamRating team = null;
                for (DataSnapshot teamSnapshot: dataSnapshot.getChildren()) {
                    TeamRating temp = teamSnapshot.getValue(TeamRating.class);
                    if (temp.teamNumber == teamNumber)
                    {
                        team = teamSnapshot.getValue(TeamRating.class);
                    }
                }
                if(team != null)
                {
                    teamNumberEditText.setText(team.teamNumber + "");
                    if (team.rating.equals("No Rating"))
                        spinner.setSelection(0);
                    else if (team.rating.equals("Green"))
                        spinner.setSelection(1);
                    else if (team.rating.equals("Yellow"))
                        spinner.setSelection(2);
                    else if (team.rating.equals("Red"))
                        spinner.setSelection(3);

                    teamNameEditText.setText(team.teamName);
                    notesEditText.setText(team.notes);
                    highGoal.setChecked(team.highGoal);
                    lowGoal.setChecked(team.lowGoal);
                    gears.setChecked(team.gears);
                    ropeLift.setChecked(team.ropeLift);
                    leftGear.setChecked(team.leftGear);
                    middleGear.setChecked(team.middleGear);
                    rightGear.setChecked(team.rightGear);
                    highGoalAuto.setChecked(team.highGoalAuto);
                    lowGoalAuto.setChecked(team.lowGoalAuto);
                    crossLine.setChecked(team.crossLine);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

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
        String num = teamNumberEditText.getText().toString();
        int teamNumber = 0;
        if(!num.equals(""))
            teamNumber = Integer.parseInt(num);

        TeamRating team = new TeamRating(teamNumber, teamNameEditText.getText().toString(), highGoal.isChecked(), lowGoal.isChecked(), gears.isChecked(), ropeLift.isChecked(),
                leftGear.isChecked(), middleGear.isChecked(), rightGear.isChecked(), highGoalAuto.isChecked(), lowGoalAuto.isChecked(), crossLine.isChecked(), (String) spinner.getSelectedItem(), notesEditText.getText().toString());

        //write team to database
        myRef.child(""+teamNumber).setValue(team);
        finish();
    }
}
