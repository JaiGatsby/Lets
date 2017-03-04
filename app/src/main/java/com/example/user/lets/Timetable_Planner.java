package com.example.user.lets;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Timetable_Planner extends AppCompatActivity {


    private DatabaseReference mDatabase;
    SharedPreferences localData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable__planner);


        localData = this.getSharedPreferences("com.example.user.lets", Context.MODE_PRIVATE);
        String UserKey = localData.getString("UserKey", "default");
        mDatabase = FirebaseDatabase.getInstance().getReference();




        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private FirebaseListAdapter<Timetable> adapter;

    public void displayChatMessages() {
        ListView listOfSlots = (ListView) findViewById(R.id.list_of_slots);

        adapter = new FirebaseListAdapter<Timetable>(this, Timetable.class,
                R.layout.time_slot, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, Timetable model, int position) {
                // Get references to the views of message.xml
                TextView days = (TextView) v.findViewById(R.id.days);
                TextView time_strt = (TextView) v.findViewById(R.id.time_strt);



                // Set their text
                days.setText(model.getDays());
                time_strt.setText(model.getStartTime());

            }
        };

        listOfSlots.setAdapter(adapter);

    }

    public void createTimeForm(View v)
    {
        Intent intent = new Intent(this, Chactivity.class);
        startActivity(intent);
    }
}
