package com.firebaseapp.interestmatcher.interestmatcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class PostDetailActivity extends AppCompatActivity {

    private TextView postTitle;
    private  TextView postAuthor;
    private TextView postContent;
    private Firebase ref;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_detail_frament);

        postTitle = (TextView) findViewById(R.id.postDetailTitle);
        postAuthor = (TextView) findViewById(R.id.postDetailAuthor);
        postContent =  (TextView) findViewById(R.id.postDetailContent);

        Bundle extras = getIntent().getExtras();
        String firebaseKey = extras.getString("firebaseKey");
        ref = new Firebase("https://interestmatcher.firebaseio.com/posts/chill").child(firebaseKey);
        populatePost();
    }

    private void populatePost(){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postDetail: dataSnapshot.getChildren()){
                    if(postDetail.getKey().equals("title")){
                        postTitle.setText(postDetail.getValue().toString());
                    }else if(postDetail.getKey().equals("author")){
                        postAuthor.setText(postDetail.getValue().toString());
                    }else if(postDetail.getKey().equals("content")){
                        postContent.setText(postDetail.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
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
