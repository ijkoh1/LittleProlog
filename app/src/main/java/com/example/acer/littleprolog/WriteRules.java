package com.example.acer.littleprolog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 29/9/2016.
 */

public class WriteRules {
    private Rules rules;
    public WriteRules(Rules ruleInput){
        this.rules = ruleInput;
    }

    public void addPredicateVersion1(String predicate, List<String> objects){
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

    public void addPredicateVersion2(String predicate, List<String> objects){
        if (this.rules.getHash().get(predicate) == null){
            Rule newRule = new MakeFacts(predicate,objects);
            this.rules.declaredRules(predicate);
            this.rules.assignRules(predicate,newRule);
        }
        else{
            this.rules.addRules(predicate,objects);
        }
    }

    public Rules getRules(){
        return this.rules;
    }
}
