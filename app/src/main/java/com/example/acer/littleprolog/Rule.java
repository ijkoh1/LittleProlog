package com.example.acer.littleprolog;

import java.util.List;

/**
 * Created by Ivan on 28/9/2016.
 * Purpose: To store the rule and its values
 */

public abstract class Rule {
    //A string which stores the predicate name
    private String predicateName;
    public Rule(String predicateName){
        /*
        @desc: To initialise the values of the predicate name
        @param: None
        @pre: None
        @post: the values are initialised
        */
        this.predicateName = predicateName;
    }

    public String getName(){
        /*
        @desc: To retrieve the name of the predicate
        @param: None
        @pre: None
        @post: the values are returned
        */
        return this.predicateName;
    }

    //An abstract method to retrieve the value of the predicate 2nd version
    public abstract List<String> getValue();
    //An abstract method to retrieve the value of the predicate 1st version
    public abstract List<List<String>> getValuePair();
    //An abstract method to add the rule into the predicate 2nd version
    public abstract void addRules(List<String> rules);
    //An abstract method to add the rule into the predicate 1st version
    public abstract void addRule(String rule);
}
