package com.example.acer.littleprolog;

import java.util.List;

/**
 * Created by Ivan on 28/9/2016.
 * Purpose: To create facts and store into the rules dictionary
 * e.g: start:- write('input length= '),
 * read(L),
 * write('input breadth= '),
 * read(B),
 write('input height= '),
 read(H),
 L >= 0,B >= 0,H >= 0,
 L < B+H,B < H+L,H < L+B,
 write('It is a triangle.').
 */

public class MakeFacts extends Rule {
    //A list of strings to store the rules
    private List<String> rule;
    public MakeFacts(String predicateName, List<String> rule){
        /*
        @desc: To initialise the values of the predicate name and values
        @param: predicateName - A string which stores the value of predicate name, rule- a list of string which store the predicate's values
        @pre: None
        @post: the values are initialised
        */
        super(predicateName);
        this.rule = rule;
    }

    public List<String> getValue(){
        /*
        @desc: To retrieve the value of the predicate
        @param: None
        @pre: None
        @post: the values are returned
        * */
        return this.rule;
    }

    public List<List<String>> getValuePair(){
        /*
        @desc: To retrieve the value of the predicate
        @param: None
        @pre: None
        @post: Nothing is returned
        * */
        return null;
    }

    public void addRule(String rule) {
        /*
        @desc: To add the rule into the rule dictionary
        @param: None
        @pre: None
        @post: The rule is added into the dicitonary
        * */
        this.rule.add(rule);
    }

    public void addRules(List<String> rules){
        /*
        @desc: This method is not implemented because it does not need to be implemented in Makefacts class
        @param: None
        @pre: None
        @post: None
        * */

    }

}
