package com.example.acer.littleprolog;

/**
 * @author: Ivan
 * @purpose: To store data objects such as currentRules, console, metaData, declaredVariables
 * */

public class TmpData implements java.io.Serializable{
    private Rules currentRules;
    private String query;
    private String console;
    private MetaData metaData;
    private DeclaredVariables declaredVariables;
    /*
    @desc: An initialization method to store values
    @param: None
    @pre: None
    @post: The input values are initialized
    */
    public TmpData(Rules currentRules, String query, String console, MetaData metaData, DeclaredVariables declaredVariables){
        this.currentRules = currentRules;
        this.query = query;
        this.console = console;
        this.metaData = metaData;
        this.declaredVariables = declaredVariables;
    }

    /*
    @desc: Get the rules object
    @param: None
    @pre: None
    @post: The rules object is returned
    :return: the rule object
    */
    public Rules getRules(){
        return this.currentRules;
    }

    /*
    @desc: Get the query object
    @param: None
    @pre: None
    @post: The query object is returned
    :return: the query object
    */
    public String getQuery(){
        return this.query;
    }

    /*
    @desc: Get the console string
    @param: None
    @pre: None
    @post: The console string is returned
    :return: A string of the console
    */
    public String getConsole(){
        return this.console;
    }

    /*
    @desc: Get the metaData object
    @param: None
    @pre: None
    @post: The metaData object is returned
    :return: the metaData object
    */
    public MetaData getMetaData(){
        return this.metaData;
    }

    /*
    @desc: Get the declaredVariables object
    @param: None
    @pre: None
    @post: The declaredVariables object is returned
    :return: the declaredVariables object
    */
    public DeclaredVariables getDeclaredVariables() {
        return this.declaredVariables;
    }
}
