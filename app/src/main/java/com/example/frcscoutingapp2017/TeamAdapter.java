package com.example.frcscoutingapp2017;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class TeamAdapter extends ArrayAdapter<TeamRating>
{
    public TeamAdapter(Context context, ArrayList<TeamRating> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TeamRating team = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_view, parent, false);
        }
        if (team != null) {
            // Lookup view for data population
            TextView teamName = (TextView) convertView.findViewById(R.id.teamName);
            TextView teamNumber = (TextView) convertView.findViewById(R.id.teamNumber);

            teamName.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
            teamNumber.setTextSize(TypedValue.COMPLEX_UNIT_SP,24);
            // Populate the data into the template view using the data object
            teamName.setText(team.teamName);
            teamNumber.setText(team.teamNumber + "");
            if(!team.rating.equals("No Rating"))
            {
                if(team.rating.equals("Green"))
                    convertView.setBackgroundColor(Color.GREEN);
                else if(team.rating.equals("Yellow"))
                    convertView.setBackgroundColor(Color.YELLOW);
                else if(team.rating.equals("Red"))
                    convertView.setBackgroundColor(Color.RED);
            }

        }
        // Return the completed view to render on screen
        return convertView;
    }
}
