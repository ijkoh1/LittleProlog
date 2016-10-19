package com.example.acer.littleprolog;

/**
 * @author: Ivan
 * @purpose: To store data such as author name, email and description
 * */

public class MetaData implements java.io.Serializable{
    //A string to store the authorName
    private String authorName;
    //A string to store the email
    private String email;
    //A string to store description
    private String description;

    /*
    @desc: An initialization method to store values
    @param: None
    @pre: None
    @post: The input values are initialized
    */
    public MetaData(String authorName, String email, String description){
        this.authorName = authorName;
        this.email = email;
        this.description = description;
    }

    /*
    @desc: Get the author's name
    @param: None
    @pre: None
    @post: The author's name is returned
    :return: A string of the auhtor's name
    */
    public String getAuthor(){
        return this.authorName;
    }

    /*
    @desc: Get the email
    @param: None
    @pre: None
    @post: The email is returned
    :return: A string of the email name
    */
    public String getEmail(){
        return this.email;
    }

    /*
    @desc: Get the description
    @param: None
    @pre: None
    @post: The description is returned
    :return: A string of the description
    */
    public String getDescription(){
        return this.description;
    }
}
