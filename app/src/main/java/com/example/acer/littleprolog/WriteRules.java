package com.example.acer.littleprolog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 28/9/2016.
 * Purpose: To add predicate name and value into the rule dictionary
 */

public class WriteRules {
    private Rules rules;
    public WriteRules(Rules ruleInput) {
        /*
        @desc: To initialise the values of the rules
        @param: None
        @pre: None
        @post: the values are initialised
        */
        this.rules = ruleInput;
    }

    public void addPredicateVersion1(String predicate, List<String> objects){
        /*
        @desc: Adds the predicate 1st version eg: animal(lion) into rule dictionary
        @param: predicate - string which stores the predicate name, objects - list of strings which stores the parameters of objects
        @pre: None
        @post: the values are added
        */
        if (this.rules.getHash().get(predicate) == null){
            List<List<String>> rule2dList = new ArrayList<List<String>>();
            rule2dList.add(objects);
            Rule newRule = new RuleFacts(predicate,rule2dList);
            this.rules.declaredRules(predicate);
            this.rules.assignRules(predicate,newRule);
        }
        else{
            this.rules.addRules(predicate,objects);
        }
    }

    public void addPredicateVersion2(String predicate, String object){
        /*
        @desc: Adds the predicate 2st version eg: start. into rule dictionary
        @param: predicate - string which stores the predicate name, objects - list of strings which stores the parameters of objects
        @pre: None
        @post: the values are added
        */
        if (this.rules.getHash().get(predicate) == null){
            List<String> ruleList = new ArrayList<>();
            if (object != null){
                ruleList.add(object);
            }
            Rule newRule = new MakeFacts(predicate,ruleList);
            this.rules.declaredRules(predicate);
            this.rules.assignRules(predicate,newRule);
        }
        else{
            this.rules.addRules1(predicate,object);
        }
    }

    public Rules getRules(){
        /*
        @desc: Retrieves the rules dictionary
        @param: None
        @pre: None
        @post: the rules dictionary are returned
        */
        return this.rules;
    }
}
