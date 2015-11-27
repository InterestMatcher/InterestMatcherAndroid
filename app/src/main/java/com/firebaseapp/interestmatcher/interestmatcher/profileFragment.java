package com.firebaseapp.interestmatcher.interestmatcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class profileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.profile_fragment, parent, false);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(MainActivity.userName);
        return view;
    }
}
