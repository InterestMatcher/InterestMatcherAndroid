package com.firebaseapp.interestmatcher.interestmatcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class postsFragment extends Fragment {

    private Firebase ref = new Firebase("https://interestmatcher.firebaseio.com/posts/chill");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.post_fragment, parent, false);
        return view;
    }
}
