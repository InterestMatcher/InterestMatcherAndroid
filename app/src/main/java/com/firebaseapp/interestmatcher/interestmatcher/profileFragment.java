package com.firebaseapp.interestmatcher.interestmatcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class profileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.profile_fragment, parent, false);
        String profUrl = "https://graph.facebook.com/" + MainActivity.id + "/picture?type=large";
        //String profUrl = "http://pngimg.com/upload/duck_PNG4998.png";

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(MainActivity.userName);

        ImageView profPic = (ImageView) view.findViewById(R.id.profPic);
        Picasso.with(getContext()).load(profUrl).centerCrop().resize(500,500).into(profPic);

        return view;
    }
}
