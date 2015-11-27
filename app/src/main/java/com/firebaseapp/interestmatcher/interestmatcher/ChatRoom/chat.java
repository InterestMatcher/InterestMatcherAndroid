package com.firebaseapp.interestmatcher.interestmatcher.ChatRoom;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class Chat {
    private String author;
    private String content;
    private String facebookID;

    public Chat(){}

    public String getAuthor(){return author;}

    public String getContent(){return content;}

    public String getFacebookID(){return facebookID;}

    public void setAuthor(String author){
        this.author = author;
    }

    public void setContent(String content){this.content = content;}

    public void setFacebookID(String id){this.facebookID = id;}
}
