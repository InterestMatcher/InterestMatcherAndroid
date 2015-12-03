package com.firebaseapp.interestmatcher.interestmatcher.Posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class Post {

    private String id;
    private String author;
    private String content;
    private String date;
    private String title;
    private String authorID;

    public Post(){}

    public String getId(){return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor(){return author;}

    public void setAuthor(String author){
        this.author = author;
    }

    public String getContent(){return content;}

    public void setTitle(String title){
        this.title = title;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getDate(){return date;}

    public void setDate(String date){this.date = date;}

    public String getTitle(){return title;}

    public String getAuthorID(){return authorID;}

    public void setAuthorID(String authorID){
        this.authorID = authorID;
    }

}
