package com.example.user.lets.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.user.lets.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InterestQuestions extends AppCompatActivity {

    private Button msendInterest;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_questions);

        msendInterest = (Button) findViewById(R.id.sendInterest);

        mDatabase = FirebaseDatabase.getInstance().getReference();

//        msendInterest.OnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }


    public void sendQuestionairre(View view){

    }



}
