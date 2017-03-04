package com.example.user.lets.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.lets.DBEvent;
import com.example.user.lets.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Events_Now extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private static final String TAG = Events_Now.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events__now);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        displayChatMessages(mDatabase.child("eventtest"));

    }

    private FirebaseListAdapter<DBEvent> adapter;

    // Remember to pass the reference to the function before calling it
    public void displayChatMessages(DatabaseReference chat){
        ListView listOfMessages = (ListView)findViewById(R.id.list_of_events);

        adapter = new FirebaseListAdapter<DBEvent>(this, DBEvent.class,
                R.layout.event_list, chat) {
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
//                Log.d(TAG,"TEST_SIZE:"+model.getNumberOfPeople());

                // Format the date before showing it
//                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
//                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }
}
