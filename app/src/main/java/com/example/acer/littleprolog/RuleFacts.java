package com.example.acer.littleprolog;

import java.util.List;

/**
 * Created by Acer on 28/9/2016.
 */

public class RuleFacts extends Rule {
    private List<List<String>> parameter;
    public RuleFacts(String predicateName, List<List<String>> parameter){
        super(predicateName);
        this.parameter = parameter;
    }

    public List<List<String>> getParameter(){
        return this.parameter;
    }

    public String getPredicate(){
        return getName();
    }

    public List<String> getValue(){
        return null;
    }

    public List<List<String>> getValuePair(){
        return getParameter();
    }

    public void addRule(String rule){

    }

    public void addRules(List<String> rules){
        this.parameter.add(rules);
    }
}
