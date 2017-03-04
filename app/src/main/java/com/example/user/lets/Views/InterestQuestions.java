package com.example.user.lets.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Button;

import com.example.user.lets.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class InterestQuestions extends AppCompatActivity {


    ArrayList<String> list ;
    CheckBox chk1,chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9,chk10;


    private Button msendInterest;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_questions);

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


    public void saveCheckboxState(View view){
        //Add checked boxes to list
        if(chk1.isChecked()){list.add(chk1.getTag().toString());}
        if(chk2.isChecked()){list.add(chk2.getTag().toString());}
        if(chk3.isChecked()){list.add(chk3.getTag().toString());}
        if(chk4.isChecked()){list.add(chk4.getTag().toString());}
        if(chk5.isChecked()){list.add(chk5.getTag().toString());}
        if(chk6.isChecked()){list.add(chk6.getTag().toString());}
        if(chk7.isChecked()){list.add(chk7.getTag().toString());}
        if(chk8.isChecked()){list.add(chk8.getTag().toString());}
        if(chk9.isChecked()){list.add(chk9.getTag().toString());}
        if(chk10.isChecked()){list.add(chk10.getTag().toString());}

        //Send to Firebase


        //Start new activity
        Intent intent = new Intent(this, Events_Now.class);
        startActivity(intent);
    }



}
