package com.firebaseapp.interestmatcher.interestmatcher.Comment;

/**
 * Created by tommypacker for HackIllinois' 2016 Clue Hunt
 */
public class Comment {

    private String author;
    private String authorID;
    private String content;
    private String date;

    public Comment(){}

    public String getAuthor(){return author;}

    public String getAuthorID(){return authorID;}

    public String getContent(){return content;}

    public String getDate(){return date;}

    public void setAuthor(String author){this.author = author;}

    public void setAuthorID(String authorID){this.authorID = authorID;}

    public void setContent(String content){this.content = content;}

    public void setDate(String date){this.date = date;}
}
