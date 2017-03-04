package com.example.user.lets;

/**
 * Created by user on 3/4/2017.
 */

public class DBEvent {
    private String Name;
    private String Date;
    private String Time;
    private int numberOfPeople;

    public DBEvent(){    }

    public DBEvent (String Name, String Date, String Time, int numberofPeople){
        this.Name = Name;
        this.Date = Date;
        this.Time = Time;
        this.numberOfPeople = numberofPeople;
    }

    public String getName(){return this.Name;}
    public void setName(String Name){this.Name=Name;}

    public String getDate(){return this.Date;}
    public void setDate(String Date){this.Date=Date;}

    public String getTime(){return this.Time;}
    public void setTime(String Time){this.Time=Time;}

    public int getNumberOfPeople(){return numberOfPeople;}
    public void setNumberOfPeople(int numberOfPeople){this.numberOfPeople=numberOfPeople;}

}
