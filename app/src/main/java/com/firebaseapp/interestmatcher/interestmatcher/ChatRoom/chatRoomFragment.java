package com.firebaseapp.interestmatcher.interestmatcher.ChatRoom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebaseapp.interestmatcher.interestmatcher.R;

import java.util.ArrayList;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class chatRoomFragment extends Fragment {

    private ArrayList<chatMessage> chatMessages;
    private chatAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.chatroom_layout, parent, false);
        ListView chatList = (ListView) view.findViewById(R.id.chatList);
        chatMessages = new ArrayList<>();
        populateChatRoom();
        adapter = new chatAdapter(getContext(), chatMessages);
        chatList.setAdapter(adapter);

        return view;
    }

    private void populateChatRoom(){
        Firebase ref = new Firebase("https://interestmatcher.firebaseio.com/chatrooms/public");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                chatMessage eachMessage = dataSnapshot.getValue(chatMessage.class);
                chatMessages.add(eachMessage);
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
}
