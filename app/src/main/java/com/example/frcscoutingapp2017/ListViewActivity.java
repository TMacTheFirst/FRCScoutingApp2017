package com.example.frcscoutingapp2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity
{
    public static ArrayList<TeamRating> teams;
    public static TeamAdapter adapter;
    ListView list;
    Button addTeamButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_list);

        teams = new ArrayList<TeamRating>();
        list = (ListView) findViewById(R.id.list);
        adapter = new TeamAdapter(this, teams);
        adapter.notifyDataSetChanged();
        adapter.setNotifyOnChange(true);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent viewIntent = new Intent(view.getContext(), RateTeamActivity.class);
                viewIntent.putExtra("name", teams.get(position).teamName);
                viewIntent.putExtra("number", teams.get(position).teamNumber);
                viewIntent.putExtra("notes", teams.get(position).notes);
                viewIntent.putExtra("index", position);
                startActivity(viewIntent);
            }
        });

        addTeamButton = (Button) findViewById(R.id.addTeam);
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
        //           intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
