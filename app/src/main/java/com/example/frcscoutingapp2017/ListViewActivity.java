package com.example.frcscoutingapp2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity
{
    public static ArrayList<TeamRating> teams;
    public static TeamAdapter adapter;
    ListView list;
    Button addTeamButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("2890");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_list);

        teams = new ArrayList<TeamRating>();
        list = (ListView) findViewById(R.id.list);
        addTeamButton = (Button) findViewById(R.id.addTeam);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                teams.clear();
                for (DataSnapshot teamSnapshot: dataSnapshot.getChildren()) {
                    TeamRating teamRating = teamSnapshot.getValue(TeamRating.class);
                    teams.add(teamRating);
                }
                adapter = new TeamAdapter(getApplicationContext(), teams);
                adapter.notifyDataSetChanged();
                adapter.setNotifyOnChange(true);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent viewIntent = new Intent(view.getContext(), ViewTeamActivity.class);
                viewIntent.putExtra("teamNumber", teams.get(position).teamNumber);
                startActivity(viewIntent);
            }
        });

        addTeamButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                addTeam();
            }
        });

    }

    public void addTeam()
    {
        Intent intent = new Intent(this, RateTeamActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        MainActivity.mAuth.signOut();
        finish();
        return;
    }
}
