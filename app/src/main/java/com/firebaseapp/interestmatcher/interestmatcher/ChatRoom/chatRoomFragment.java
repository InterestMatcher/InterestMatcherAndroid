package com.firebaseapp.interestmatcher.interestmatcher.ChatRoom;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

        return view;
    }
}
