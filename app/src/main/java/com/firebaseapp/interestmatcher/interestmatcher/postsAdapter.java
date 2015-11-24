package com.firebaseapp.interestmatcher.interestmatcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class postsAdapter extends ArrayAdapter<Post> {
    public postsAdapter(Context context, ArrayList<Post> Posts){
        super(context, 0, Posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Post post = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.post_item, parent, false);
        }
        TextView postTitle = (TextView) convertView.findViewById(R.id.postTitle);
        postTitle.setText(post.getTitle());
        return convertView;
    }
}
