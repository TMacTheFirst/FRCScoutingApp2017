package com.example.frcscoutingapp2017;

/**
 * Created by tmccorkill on 2/27/17.
 */

public class TeamRating
{
    //id is unique id for nosql database
    public int id = 0;
    public int teamNumber = 0;
    public String teamName = "";
    public boolean highGoal, lowGoal, gears, ropeLift = false;
    public boolean leftGear, middleGear, rightGear, highGoalAuto, lowGoalAuto, crossLine = false;
    public String rating, notes = "";

    public TeamRating()
    {

    }
}
