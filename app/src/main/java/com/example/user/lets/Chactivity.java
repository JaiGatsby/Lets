package com.example.user.lets;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chactivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference chatroom;
//    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chactivity);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        String eventId = (getIntent().getStringExtra("USER_KEY")=="")?"Fail":getIntent().getStringExtra("USER_KEY");
//        String eventId = "Please";
        chatroom = mDatabase.child("ChatRooms").child(eventId).child("Chat");
        // The basic idea is, once someone presses push, we send that data to the server
        // and empty the input field
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText input = (EditText)findViewById(R.id.input);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                chatroom.push()
                        .setValue(new ChatMessage(input.getText().toString(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName())
                        );
                input.setText(""); // clear the input
                // where eventName is the id of the event

            }
        });

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is: " + value);
                // Where chatroom is the id of the chatroom/event
                displayChatMessages(chatroom);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private FirebaseListAdapter<ChatMessage> adapter;

    public void displayChatMessages(DatabaseReference Chat){
        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, Chat) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }


}
