package com.example.acer.littleprolog;

/**
 * Created by Acer on 14/10/2016.
 */

public class MetaData implements java.io.Serializable{
    private String authorName;
    private String email;
    private String description;

    public MetaData(String authorName, String email, String description){
        this.authorName = authorName;
        this.email = email;
        this.description = description;
    }

    public String getAuthor(){
        return this.authorName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getDescription(){
        return this.description;
    }
}
