package com.example.user.lets.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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


public class EventsFragment extends Fragment {
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "page";

    private String title;
    private int page;

    private DatabaseReference mDatabase;
    SharedPreferences localData;
    private static final String TAG = MainActivity.class.getName();


    public EventsFragment() {
        // Required empty public constructor
    }


    public static EventsFragment newInstance(String title, int page) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, title);
        args.putInt(ARG_PARAM2, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_PARAM1);
            page = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_events, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        localData = getActivity().getSharedPreferences("com.example.user.lets", Context.MODE_PRIVATE);
        displayEvents(mDatabase.child("ChatRooms"), rootView);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);


                displayEvents(mDatabase.child("ChatRooms"),rootView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        return rootView;
    }


    private FirebaseListAdapter<DBEvent> adapter;

    // Remember to pass the reference to the function before calling it
    public void displayEvents(DatabaseReference chat, View view){
        ListView listOfMessages = (ListView) view.findViewById(R.id.list_of_events);

        adapter = new FirebaseListAdapter<DBEvent>(getActivity(), DBEvent.class,
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

                // Format the date before showing it
//                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
//                        model.getMessageTime()));
            }
        };

        listOfMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Chactivity.class);
                String key = adapter.getItem(position).getId();
                Log.d(TAG,"DEBUG_KEY"+key);
                intent.putExtra("USER_KEY",key);
                mDatabase.child("Users").child(localData.getString("UserKey", "default"))
                        .child("Chatroom").child(key).setValue(adapter.getItem(position));
                startActivity(intent);
            }
        });

        if(listOfMessages != null)
            listOfMessages.setAdapter(adapter);
    }
}
