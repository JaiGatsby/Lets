package com.example.user.lets.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.lets.Chactivity;
import com.example.user.lets.DBEvent;
import com.example.user.lets.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Events_Now extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private static final String TAG = Events_Now.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events__now);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        displayEvents(mDatabase.child("ChatRooms"));

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);


                displayEvents(mDatabase.child("ChatRooms"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }



    private FirebaseListAdapter<DBEvent> adapter;

    // Remember to pass the reference to the function before calling it
    public void displayEvents(DatabaseReference Event){
        ListView listOfMessages = (ListView)findViewById(R.id.list_of_events);

        adapter = new FirebaseListAdapter<DBEvent>(this, DBEvent.class,
                R.layout.event_list, Event) {
            @Override
            protected void populateView(View v, DBEvent model, int position) {
                // Get references to the views of message.xml
                TextView eventName = (TextView)v.findViewById(R.id.event_name);
                TextView eventSize = (TextView)v.findViewById(R.id.event_size);
                TextView eventTime = (TextView)v.findViewById(R.id.event_time);
                TextView eventDate = (TextView)v.findViewById(R.id.event_date);

                // Set their text
                eventName.setText(model.getName());
                eventTime.setText(model.getTime());
                eventDate.setText(model.getDate());
                eventSize.setText(String.valueOf(model.getNumberOfPeople()));

                // Format the date before showing it
//                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
//                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }
}
