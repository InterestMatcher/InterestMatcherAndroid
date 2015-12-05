package com.firebaseapp.interestmatcher.interestmatcher.Posts;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebaseapp.interestmatcher.interestmatcher.MainActivity;
import com.firebaseapp.interestmatcher.interestmatcher.Posts.Post;
import com.firebaseapp.interestmatcher.interestmatcher.Posts.PostDetailActivity;
import com.firebaseapp.interestmatcher.interestmatcher.Posts.postsAdapter;
import com.firebaseapp.interestmatcher.interestmatcher.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class postsFragment extends Fragment {

    private postsAdapter adapter;
    private ArrayList<Post> posts;
    private EditText title;
    private EditText content;
    private Dialog dialog;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.post_fragment, parent, false);
        ListView postsList = (ListView) view.findViewById(R.id.postsList);
        posts = new ArrayList<>();
        populatePosts();
        adapter = new postsAdapter(this.getContext(), posts);
        postsList.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.addPostFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(getContext());
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
                bundle.putString("firebaseKey", adapter.getItem(position).getId());
                Intent intent = new Intent(getActivity(), PostDetailActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        return view;
    }

    private void populatePosts(){
        Firebase ref = new Firebase("https://interestmatcher.firebaseio.com/posts/chill");

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Post postAdded = dataSnapshot.getValue(Post.class);
                postAdded.setId(dataSnapshot.getKey());
                posts.add(0, postAdded);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    private void makeNewPost(){
        Firebase ref = new Firebase("https://interestmatcher.firebaseio.com/posts/chill");
        Firebase postRef = ref.push();

        Post newPost = new Post();

        String newPostTitle = title.getText().toString().trim();
        String newPostContent = content.getText().toString().trim();
        if(newPostContent.length() == 0 || newPostTitle.length() == 0 || newPostTitle.length() > 50 || newPostContent.length() > 2000){
            Toast.makeText(getContext(), "Please enter a valid post", Toast.LENGTH_SHORT).show();
        }else{
            String id = postRef.getKey();

            newPost.setId(id);
            newPost.setTitle(newPostTitle);
            newPost.setContent(newPostContent);
            newPost.setAuthor(MainActivity.userName);
            newPost.setAuthorID(MainActivity.id);
            newPost.setDate(getCurrentDate());

            postRef.setValue(newPost);
            dialog.dismiss();
        }
    }

    private String getCurrentDate(){
        long currentTime = System.currentTimeMillis();
        Date currentDate = new Date(currentTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        return sdf.format(currentDate);
    }
}
