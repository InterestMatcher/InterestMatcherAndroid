package com.firebaseapp.interestmatcher.interestmatcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class postsFragment extends Fragment {

    private postsAdapter adapter;
    private ArrayList<Post> posts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.post_fragment, parent, false);
        ListView postsList = (ListView) view.findViewById(R.id.postsList);
        posts = new ArrayList<>();
        populatePosts();
        adapter = new postsAdapter(this.getContext(), posts);
        postsList.setAdapter(adapter);

        return view;
    }

    private void populatePosts(){
        Firebase ref = new Firebase("https://interestmatcher.firebaseio.com/posts/chill");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot eachPost : dataSnapshot.getChildren()){
                    Post singlePost = eachPost.getValue(Post.class);
                    posts.add(singlePost);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
