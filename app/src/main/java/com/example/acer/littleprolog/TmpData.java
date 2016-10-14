package com.example.acer.littleprolog;

/**
 * Created by Acer on 10/10/2016.
 */

public class TmpData implements java.io.Serializable{
    private Rules currentRules;
    private String query;
    private String console;
    public TmpData(Rules currentRules, String query, String console){
        this.currentRules = currentRules;
        this.query = query;
        this.console = console;
    }

    public Rules getRules(){
        return this.currentRules;
    }

    public String getQuery(){
        return this.query;
    }

    public String getConsole(){
        return this.console;
    }
}
