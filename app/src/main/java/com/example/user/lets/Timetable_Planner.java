package com.example.user.lets;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Timetable_Planner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable__planner);
    }

    public void createTimeForm(View view)
    {
        Intent intent = new Intent(this, Timetable_Form.class);
        startActivity(intent);
    }
}
