package com.example.acer.littleprolog;

import java.util.List;

/**
 * Created by Ivan on 29/9/2016.
 * Purpose: To store the 1st version of rule eg: animal(lion0, animal(lion,cub).
 */

public class RuleFacts extends Rule {
    //A list of list of strings which store the pairs of predicate values
    private List<List<String>> parameter;
    public RuleFacts(String predicateName, List<List<String>> parameter){
        /*
        @desc: To initialise the values of the predicate name and values
        @param: predicateName - A string which stores the value of predicate name, rule- a list of string which store the predicate's values
        @pre: None
        @post: the values are initialised
        */
        super(predicateName);
        this.parameter = parameter;
    }

    public List<List<String>> getParameter(){
        /*
        @desc: Retrieves the predicate value
        @param: None
        @pre: None
        @post: the predicate value is returned
        * */
        return this.parameter;
    }

    public String getPredicate(){
        /*
        @desc: Retrieves the predicate name
        @param: None
        @pre: None
        @post: predicate name is returned
        * */
        return getName();
    }

    public List<String> getValue()
        /*
        @desc: To retrieve the value of the predicate
        @param: None
        @pre: None
        @post: the values are returned
        * */{
        return null;
    }

    public List<List<String>> getValuePair(){
        /*
        @desc: To retrieve the value of the predicate
        @param: None
        @pre: None
        @post: Nothing is returned
        * */
        return getParameter();
    }

    public void addRule(String rule){
        /*
        @desc: This method is not implemented because it does not need to be implemented in Makefacts class
        @param: None
        @pre: None
        @post: None
        * */
    }

    public void addRules(List<String> rules){
        /*
        @desc: To add the rule into the rule dictionary
        @param: None
        @pre: None
        @post: The rule is added into the dicitonary
        * */
        this.parameter.add(rules);
    }
}
