package com.firebaseapp.interestmatcher.interestmatcher.ChatRoom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebaseapp.interestmatcher.interestmatcher.R;

import java.util.ArrayList;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class chatAdapter extends ArrayAdapter<chatMessage> {

    public chatAdapter(Context context, ArrayList<chatMessage> chatMessages){
        super(context, 0, chatMessages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        chatMessage chatMessage = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.chat_item, parent, false);
        }
        TextView chatContent = (TextView) convertView.findViewById(R.id.chatTitle);
        TextView chatAuthor = (TextView) convertView.findViewById(R.id.chatAuthor);
        chatContent.setText(chatMessage.getContent());
        chatAuthor.setText(chatMessage.getAuthor());
        return convertView;
    }

}
