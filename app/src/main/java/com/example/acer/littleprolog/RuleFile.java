package com.example.acer.littleprolog;

import java.util.HashMap;

/**
 * Created by Ivan on 28/9/2016.
 * Purpose: To store the rule files created by the user
 */

public class RuleFile {
    //A dictionary to store the rules
    private HashMap<String,String> filesList;
    public RuleFile(){
        /*
        @desc: To initialise the values of the fileList
        @param: None
        @pre: None
        @post: the values are initialised
        */
        filesList = new HashMap<String,String>();
    }

    public void addFile(String fileName){
        /*
        @desc: To insert rules into ruleFile dictionary
        @param: None
        @pre: None
        @post: the values are added into ruleFile
        */
        if (!this.filesList.containsKey(fileName)){
            this.filesList.put(fileName,null);
        }
    }

    public void addContent(String fileName, String content){
        /*
        @desc: To insert rules into ruleFile dictionary
        @param: None
        @pre: None
        @post: the values are added into ruleFile
        */
        if (!this.filesList.containsKey(fileName)){
            this.filesList.put(fileName,content);
        }
    }

    public String getContent(String fileName){
        /*
        @desc: To retrieve the text from the ruleFile dictionary
        @param: None
        @pre: None
        @post: the values are retrieved
        */
        if (this.filesList.containsKey(fileName)){
            return this.filesList.get(fileName);
        }
        return null;
    }
}
