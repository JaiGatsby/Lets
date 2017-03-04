package com.example.user.lets.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.NumberPicker;

import com.example.user.lets.R;
import com.example.user.lets.Timetable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Timetable_Form extends AppCompatActivity {

    CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7;
    NumberPicker np_hours_s, np_min_s, np_hours_e, np_min_e;
    String[] hours, minutes;
    int daysFree = 0;
    private static final String TAG = Timetable_Form.class.getName();

    private DatabaseReference mDatabase;

    SharedPreferences localData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable__form);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        localData = this.getSharedPreferences("com.example.user.lets", Context.MODE_PRIVATE);

        chk1=(CheckBox)findViewById(R.id.check_mon);
        chk2=(CheckBox)findViewById(R.id.check_tue);
        chk3=(CheckBox)findViewById(R.id.check_wed);
        chk4=(CheckBox)findViewById(R.id.check_thu);
        chk5=(CheckBox)findViewById(R.id.check_fri);
        chk6=(CheckBox)findViewById(R.id.check_sat);
        chk7=(CheckBox)findViewById(R.id.check_sun);

        np_hours_s = (NumberPicker)findViewById(R.id.np_h_start);
        np_min_s = (NumberPicker)findViewById(R.id.np_m_start);
        np_hours_e  = (NumberPicker)findViewById(R.id.np_h_end);
        np_min_e = (NumberPicker)findViewById(R.id.np_m_end);

        hours = new String[24];
        minutes = new String[12];

        for(int i = 0; i < 24; i++)
            hours[i] = Integer.toString(i);

        for(int i = 0; i < 12; i+=1)
            minutes[i] = Integer.toString(i*5);

        np_hours_s.setMinValue(0);
        np_hours_s.setMaxValue(hours.length-1);
        np_hours_s.setDisplayedValues(hours);

        np_hours_e.setMinValue(0);
        np_hours_e.setMaxValue(hours.length-1);
        np_hours_e.setDisplayedValues(hours);

        np_min_s.setMinValue(0);
        np_min_s.setMaxValue(minutes.length-1);
        np_min_s.setDisplayedValues(minutes);

        np_min_e.setMinValue(0);
        np_min_e.setMaxValue(minutes.length-1);
        np_min_e.setDisplayedValues(minutes);

    }

    public void sendForm(View view)
    {
        String UserKey = localData.getString("UserKey", "default");
        DatabaseReference curUser = mDatabase.child("Users").child(UserKey);
        String startTime = Integer.toString(np_hours_s.getValue()) + Integer.toString(np_min_s.getValue());
        String endTime = Integer.toString(np_hours_e.getValue()) + Integer.toString(np_min_e.getValue());

        curUser.child("TimeTable").push().setValue(new Timetable(Integer.toString(daysFree), startTime, endTime));
        Intent intent = new Intent(this, Events_Now.class);
        startActivity(intent);
    }


    public void checkTicked(View view)
    {
        boolean checked = ((CheckBox)view).isChecked();

        switch(view.getId())
        {
            case R.id.check_mon:
                if(checked)
                    daysFree += 1;
                else
                    daysFree -= 1;
                break;

            case R.id.check_tue:
                if(checked)
                    daysFree += 10;
                else
                    daysFree -= 10;
                break;

            case R.id.check_wed:
                if(checked)
                    daysFree += 100;
                else
                    daysFree -= 100;
                break;

            case R.id.check_thu:
                if(checked)
                    daysFree += 1000;
                else
                    daysFree -= 1000;
                break;

            case R.id.check_fri:
                if(checked)
                    daysFree += 10000;
                else
                    daysFree -= 10000;
                break;

            case R.id.check_sat:
                if(checked)
                    daysFree += 100000;
                else
                    daysFree -= 100000;
                break;

            case R.id.check_sun:
                if(checked)
                    daysFree += 1000000;
                else
                    daysFree -= 1000000;
                break;
        }
    }
}
