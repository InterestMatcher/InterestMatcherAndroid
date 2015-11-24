package com.firebaseapp.interestmatcher.interestmatcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class mapFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.map_fragment, parent, false);
        return view;
    }
}
