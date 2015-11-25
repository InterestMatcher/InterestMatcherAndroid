package com.firebaseapp.interestmatcher.interestmatcher;

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

    public String getAuthor(){return author;}

    public String getContent(){return content;}

    public String getDate(){return date;}

    public String getTitle(){return title;}

    public String getAuthorID(){return authorID;}

}
