package com.firebaseapp.interestmatcher.interestmatcher.Posts;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Post {

    private String ID;
    private String author;
    private String content;
    private String date;
    private String title;
    private String authorID;

    public Post(){}

    public String getID(){return ID;}

    public void setID(String id) {
        this.ID = id;
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

    public String getTitle(){return title;}

    public String getAuthorID(){return authorID;}

    public void setAuthorID(String authorID){
        this.authorID = authorID;
    }

}
