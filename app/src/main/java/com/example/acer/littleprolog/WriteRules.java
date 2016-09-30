package com.example.acer.littleprolog;

/**
 * Created by Acer on 29/9/2016.
 */

public class WriteRules {
    private RuleFile ruleFile;
    public WriteRules(){
        ruleFile = new RuleFile();
    }

    public void writeFile(String fileName, String content){
        ruleFile.addContent(fileName,content);
    }
}
