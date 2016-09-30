package com.example.acer.littleprolog;

import java.util.List;

/**
 * Created by Acer on 28/9/2016.
 */

public class MakeFacts extends Rule {
    private List<String> rule;
    public MakeFacts(String predicateName, List<String> rule){
        super(predicateName);
        this.rule = rule;
    }

    public List<String> getValue(){
        return this.rule;
    }

    public List<List<String>> getValuePair(){
        return null;
    }

    public void addRule(String rule) {
        this.rule.add(rule);
    }

    public void addRules(List<String> rules){

    }

}
