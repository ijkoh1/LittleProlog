package com.example.acer.littleprolog;

import java.util.HashMap;

/**
 * Created by Acer on 29/9/2016.
 */

public class RuleFile {
    private HashMap<String,String> filesList;
    public RuleFile(){
        filesList = new HashMap<String,String>();
    }

    public void addFile(String fileName){
        if (!this.filesList.containsKey(fileName)){
            this.filesList.put(fileName,null);
        }
    }

    public void addContent(String fileName, String content){
        if (!this.filesList.containsKey(fileName)){
            this.filesList.put(fileName,content);
        }
    }

    public String getContent(String fileName){
        if (this.filesList.containsKey(fileName)){
            return this.filesList.get(fileName);
        }
        return null;
    }
}
