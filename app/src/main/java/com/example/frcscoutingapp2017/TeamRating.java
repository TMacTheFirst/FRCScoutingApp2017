package com.example.frcscoutingapp2017;

/**
 * Created by tmccorkill on 2/27/17.
 */

public class TeamRating
{
    public int teamNumber = 0;
    public String teamName = "";
    public boolean highGoal, lowGoal, gears, ropeLift = false;
    public boolean leftGear, middleGear, rightGear, highGoalAuto, lowGoalAuto, crossLine = false;
    public String rating, notes = "";

    public TeamRating()
    {

    }

    public TeamRating(int teamNumber, String teamName, boolean highGoal, boolean lowGoal,
                      boolean gears, boolean ropeLift, boolean leftGear, boolean middleGear,
                      boolean rightGear, boolean highGoalAuto, boolean lowGoalAuto, boolean crossLine,
                      String rating, String notes)
    {
        this.teamNumber = teamNumber;
        this.teamName = teamName;
        this.highGoal = highGoal;
        this.lowGoal = lowGoal;
        this.gears = gears;
        this.ropeLift = ropeLift;
        this.leftGear = leftGear;
        this.middleGear = middleGear;
        this.rightGear = rightGear;
        this.highGoalAuto = highGoalAuto;
        this.lowGoalAuto = lowGoalAuto;
        this.crossLine = crossLine;
        this.rating = rating;
        this.notes = notes;
    }

}
