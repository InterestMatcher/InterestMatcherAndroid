package com.firebaseapp.interestmatcher.interestmatcher;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
    private EditText title;
    private EditText content;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.post_fragment, parent, false);
        final ListView postsList = (ListView) view.findViewById(R.id.postsList);
        posts = new ArrayList<>();
        populatePosts();
        adapter = new postsAdapter(this.getContext(), posts);
        postsList.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.addPostFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.new_post_dialog);
                dialog.setTitle("New Post");
                title = (EditText) dialog.findViewById(R.id.newPostTitle);
                content = (EditText) dialog.findViewById(R.id.newPostContent);
                Button submitPostButton = (Button) dialog.findViewById(R.id.submitNewPost);
                submitPostButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makeNewPost();
                    }
                });
                dialog.show();
            }
        });

        postsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("firebaseKey", adapter.getItem(position).getID());
                Intent intent = new Intent(getActivity(), PostDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    private void populatePosts(){
        Firebase ref = new Firebase("https://interestmatcher.firebaseio.com/posts/chill");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot eachPost : dataSnapshot.getChildren()) {
                    Post singlePost = eachPost.getValue(Post.class);
                    singlePost.setID(eachPost.getKey());
                    posts.add(singlePost);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void makeNewPost(){
        String newPostTitle = title.getText().toString();
        String newPostContent = content.getText().toString();
        Post newPost = new Post();
        newPost.setTitle(newPostTitle);
        newPost.setContent(newPostContent);
        newPost.setAuthor(MainActivity.userName);

        Firebase ref = new Firebase("https://interestmatcher.firebaseio.com/posts/chill");
        ref.push().setValue(newPost);
    }
}
