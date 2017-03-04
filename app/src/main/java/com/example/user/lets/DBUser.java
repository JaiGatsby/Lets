package com.example.user.lets;

/**
 * Created by user on 3/4/2017.
 */

public class DBUser {
    private String UserKey;
    private String UserName;

    public DBUser(String Key, String Name){
        this.UserKey = Key;
        this.UserName = Name;
    }

    public DBUser(){

    }

    public String getUserKey(){return UserKey; }

    public void setUserKey(String Key){this.UserKey = Key;}

    public String getUserName(){return UserName;}

    public void setUserName(String Name){this.UserName=Name;}
}
