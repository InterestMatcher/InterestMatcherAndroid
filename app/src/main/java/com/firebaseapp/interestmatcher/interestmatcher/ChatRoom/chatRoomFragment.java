package com.firebaseapp.interestmatcher.interestmatcher.ChatRoom;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebaseapp.interestmatcher.interestmatcher.MainActivity;
import com.firebaseapp.interestmatcher.interestmatcher.R;

import java.util.ArrayList;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class chatRoomFragment extends Fragment {

    private ArrayList<chatMessage> chatMessages;
    private chatAdapter adapter;
    private Dialog dialog;
    private EditText messageContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.chatroom_layout, parent, false);
        Firebase.setAndroidContext(getContext());
        ListView chatList = (ListView) view.findViewById(R.id.chatList);
        chatMessages = new ArrayList<>();
        populateChatRoom();
        adapter = new chatAdapter(getContext(), chatMessages);
        chatList.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.addChatFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.new_chat_dialog);
                dialog.setTitle("New Chat");
                messageContent = (EditText) dialog.findViewById(R.id.newChatContent);
                Button submitPostButton = (Button) dialog.findViewById(R.id.submitNewPost);
                submitPostButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makeNewChat();
                    }
                });
                dialog.show();
            }
        });

        return view;
    }

    private void populateChatRoom(){
        Firebase ref = new Firebase("https://interestmatcher.firebaseio.com/chatrooms/public");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                chatMessage eachMessage = dataSnapshot.getValue(chatMessage.class);
                chatMessages.add(0, eachMessage);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    private void makeNewChat(){
        Firebase ref = new Firebase("https://interestmatcher.firebaseio.com/chatrooms/public");

        String messageBody = messageContent.getText().toString();
        String adjusted = messageBody.trim();
        if(adjusted.length() == 0 || adjusted.length() > 141){
            Toast.makeText(getContext(), "Please enter a valid message", Toast.LENGTH_SHORT).show();
        }else{
            chatMessage newChat = new chatMessage();
            newChat.setContent(adjusted);
            newChat.setAuthor(MainActivity.userName);
            newChat.setFacebookID(MainActivity.id);

            ref.push().setValue(newChat);
            dialog.dismiss();
        }
    }
}
