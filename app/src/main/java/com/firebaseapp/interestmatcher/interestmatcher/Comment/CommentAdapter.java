package com.firebaseapp.interestmatcher.interestmatcher.Comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebaseapp.interestmatcher.interestmatcher.ChatRoom.chatMessage;
import com.firebaseapp.interestmatcher.interestmatcher.R;

import java.util.ArrayList;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class CommentAdapter extends ArrayAdapter<Comment> {

    public CommentAdapter(Context context, ArrayList<Comment> comments){
        super(context, 0, comments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Comment comment = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_item, parent, false);
        }
        TextView commentContent = (TextView) convertView.findViewById(R.id.commentTitle);
        TextView commentAuthor = (TextView) convertView.findViewById(R.id.commentAuthor);
        commentContent.setText(comment.getContent());
        commentAuthor.setText(comment.getAuthor());
        return convertView;
    }
}
