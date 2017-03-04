package com.example.user.lets.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import com.example.user.lets.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InterestQuestions extends AppCompatActivity {


    ArrayList<String> list ;
    CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9,chk10;

    private SharedPreferences localData;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        localData = this.getSharedPreferences("com.example.user.lets", Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_questions);

        // Sets the mDatabase to the root of the database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        list = new ArrayList<>();

        chk1=(CheckBox)findViewById(R.id.check_Football);
        chk2=(CheckBox)findViewById(R.id.check_Basketball);
        chk3=(CheckBox)findViewById(R.id.check_Card);
        chk4=(CheckBox)findViewById(R.id.check_Movies);
        chk5=(CheckBox)findViewById(R.id.check_Clubbing);
        chk6=(CheckBox)findViewById(R.id.check_Drinking);
        chk7=(CheckBox)findViewById(R.id.check_Eatingin);
        chk8=(CheckBox)findViewById(R.id.check_Eatingout);
        chk9=(CheckBox)findViewById(R.id.check_Hackathons);
        chk10=(CheckBox)findViewById(R.id.check_Hiking);
    }

    public void onCheckboxClick(View view)
    {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()){
            case R.id.check_Football:
                if(checked)
                    list.add("Football");
                else
                    list.remove("Football");

                break;

            case R.id.check_Basketball:
                if(checked)
                    list.add("Basketball");
                else
                    list.remove("Basketball");

            case R.id.check_Card:
                if(checked)
                    list.add("Card");
                else
                    list.remove("Card");

                break;

            case R.id.check_Movies:
                if(checked)
                    list.add("Movies");
                else
                    list.remove("Movies");

                break;
            case R.id.check_Clubbing:
                if(checked)
                    list.add("Clubbing");
                else
                    list.remove("Clubbing");

                break;
            case R.id.check_Drinking:
                if(checked)
                    list.add("Drinking");
                else
                    list.remove("Drinking");

                break;
            case R.id.check_Eatingin:
                if(checked)
                    list.add("Eatingin");
                else
                    list.remove("Eatingin");

                break;
            case R.id.check_Eatingout:
                if(checked)
                    list.add("Eatingout");
                else
                    list.remove("Eatingout");

                break;
            case R.id.check_Hackathons:
                if(checked)
                    list.add("Hackathons");
                else
                    list.remove("Hackathons");

                break;

            case R.id.check_Hiking:
                if(checked)
                    list.add("Hiking");
                else
                    list.remove("Hiking");

                break;
        }
    }

    // On Click for Submit button
    public void saveCheckboxState(View view){
        //Add checked boxes to list


        //Send to Firebase

        //Gets the UserKey from the sharedpreferences
        String UserKey = localData.getString("UserKey","default");

        DatabaseReference User = mDatabase.child("Users").child(UserKey).child("Interests");

        User.setValue(list);

        //Start new activity
        Intent intent = new Intent(this, Events_Now.class);
        startActivity(intent);
    }



}
