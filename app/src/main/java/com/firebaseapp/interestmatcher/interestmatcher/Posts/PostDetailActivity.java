package com.firebaseapp.interestmatcher.interestmatcher.Posts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebaseapp.interestmatcher.interestmatcher.Comment.Comment;
import com.firebaseapp.interestmatcher.interestmatcher.Comment.CommentAdapter;
import com.firebaseapp.interestmatcher.interestmatcher.MainActivity;
import com.firebaseapp.interestmatcher.interestmatcher.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class PostDetailActivity extends AppCompatActivity {

    private TextView postTitle;
    private TextView postAuthor;
    private TextView postContent;
    private EditText commentBox;
    private Firebase ref;
    private ArrayList<Comment> comments;
    private ArrayAdapter<Comment> adapter;
    private String firebaseKey;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_detail_layout);

        postTitle = (TextView) findViewById(R.id.postDetailTitle);
        postAuthor = (TextView) findViewById(R.id.postDetailAuthor);
        postContent =  (TextView) findViewById(R.id.postDetailContent);
        commentBox = (EditText) findViewById(R.id.commentBox);

        Bundle extras = getIntent().getExtras();
        firebaseKey = extras.getString("firebaseKey");
        ref = new Firebase("https://interestmatcher.firebaseio.com/posts/chill").child(firebaseKey);
        populatePost();

        ListView commentList = (ListView) findViewById(R.id.commentList);
        comments = new ArrayList<>();
        populateComments();
        adapter = new CommentAdapter(getApplicationContext(), comments);
        commentList.setAdapter(adapter);

        Button newCommentButton = (Button) findViewById(R.id.newCommentButton);
        newCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewComment();
            }
        });
    }

    private void populatePost(){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postDetail : dataSnapshot.getChildren()) {
                    if (postDetail.getKey().equals("title")) {
                        postTitle.setText(postDetail.getValue().toString());
                    } else if (postDetail.getKey().equals("author")) {
                        postAuthor.setText(postDetail.getValue().toString());
                    } else if (postDetail.getKey().equals("content")) {
                        postContent.setText(postDetail.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    private void populateComments(){
        Firebase commentRef = new Firebase("https://interestmatcher.firebaseio.com/comments").child(firebaseKey);
        commentRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Comment oldComment = dataSnapshot.getValue(Comment.class);
                comments.add(0, oldComment);
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

    private void addNewComment(){
        Firebase newCommentRef = new Firebase("https://interestmatcher.firebaseio.com/comments").child(firebaseKey);
        Firebase pushCommentRef = newCommentRef.push();

        String commentContent = commentBox.getText().toString().trim();
        if(commentContent.length() <= 0 || commentContent.length() > 141){ //we're one better than Twitter
            Toast.makeText(getApplicationContext(), "Please enter a valid comment", Toast.LENGTH_SHORT).show();
        }else{
            Comment newComment = new Comment();
            newComment.setContent(commentContent);
            newComment.setAuthor(MainActivity.userName);
            newComment.setAuthorID(MainActivity.id);
            newComment.setDate(getCurrentDate());

            pushCommentRef.setValue(newComment);
        }
        commentBox.getText().clear();
        hideSoftKeyboard(commentBox);
    }

    private String getCurrentDate(){
        long currentTime = System.currentTimeMillis();
        Date currentDate = new Date(currentTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        return sdf.format(currentDate);
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = NavUtils.getParentActivityIntent(this);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                NavUtils.navigateUpTo(this, intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
