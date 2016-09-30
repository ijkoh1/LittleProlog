package com.example.acer.littleprolog;

import java.util.List;

/**
 * Created by Acer on 28/9/2016.
 */

public abstract class Rule {
    private String predicateName;
    public Rule(String predicateName){
        this.predicateName = predicateName;
    }

    public String getName(){
        return this.predicateName;
    }

    public abstract List<String> getValue();
    public abstract List<List<String>> getValuePair();
    public abstract void addRules(List<String> rules);
    public abstract void addRule(String rule);
}
