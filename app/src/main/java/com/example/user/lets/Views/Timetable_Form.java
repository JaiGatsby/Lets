package com.example.user.lets.Views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.user.lets.R;

public class Timetable_Form extends AppCompatActivity {

    CheckBox chk1, chk2, chk3, chk4, chk5, chk6, chk7;
    private static final String TAG = Timetable_Form.class.getName();
    EditText tm_start, tm_end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable__form);

        chk1=(CheckBox)findViewById(R.id.check_mon);
        chk2=(CheckBox)findViewById(R.id.check_tue);
        chk3=(CheckBox)findViewById(R.id.check_wed);
        chk4=(CheckBox)findViewById(R.id.check_thu);
        chk5=(CheckBox)findViewById(R.id.check_fri);
        chk6=(CheckBox)findViewById(R.id.check_sat);
        chk7=(CheckBox)findViewById(R.id.check_sun);

        tm_start = (EditText)findViewById(R.id.time_start);
        tm_end = (EditText)findViewById(R.id.time_start);

    }

    public void sendForm(View view)
    {
        Log.d(TAG,tm_start.getText().toString());
    }
}
