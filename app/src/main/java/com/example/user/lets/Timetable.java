package com.example.user.lets;

/**
 * Created by Asfandyar on 04-Mar-17.
 */

public class Timetable {
    private String days;
    private String startTime;
    private String endTime;

    public Timetable(String days_,String startTime_, String endTime_ )
    {
        days = days_;
        startTime = startTime_;
        endTime = endTime_;
    }

    public String getDays(){return days;}
    public void setDays(String days_){days=days_;}
    public String getStartTime(){return startTime;}
    public void setStartTime(String days_){startTime=days_;}
    public String getEndTime(){return endTime;}
    public void setEndTime(String days_){endTime=days_;}
}
