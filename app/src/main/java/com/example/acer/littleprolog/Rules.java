package com.example.acer.littleprolog;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ivan on 28/9/2016.
 * Purpose: To store and extract the rules in a dictionary
 */

public class Rules {
    //A dictionary which stores the rules
    private HashMap<String, Rule> rulesDict;

    public Rules(){
        /*
        @desc: To initialise the values of the rule dictionary
        @param: None
        @pre: None
        @post: the values are initialised
        */
        rulesDict = new HashMap<String, Rule>();
    }

    public boolean isRule(String predicateName){
        /*
        @desc: To check if the predicate is in the rules dictionary
        @param: predicateName - a string which stores the name of the predicate
        @pre: the rules dictionary should exist
        @post: the values are initialised
        */
        return rulesDict.containsKey(predicateName);
    }

    public HashMap<String,Rule> getHash(){
        /*
        @desc: To retrieve the rules dictionary
        @param: None
        @pre: None
        @post: the values are returned
        */
        return this.rulesDict;
    }

    public void clearHash(){
        /*
        @desc: To clear the value of the rules dictionary
        @param: None
        @pre: None
        @post: the rules dictionary are empty
        */
        rulesDict.clear();
    }

    public void declaredRules(String predicateName){
        /*
        @desc: To insert predicate into the rules dictionary
        @param: predicateName - string which stores the predicate name
        @pre: None
        @post: the predicate are added into rules dictionary
        */
        if (!rulesDict.containsKey(predicateName)){
            rulesDict.put(predicateName,null);
        }
    }

    public void removeRule(String predicateName){
        /*
        @desc: To remove predicate from the rules dictionary
        @param: predicateName - string which stores the predicate name
        @pre: None
        @post: the predicate are removed from rules dictionary
        */
        if (rulesDict.containsKey(predicateName))
        {
            rulesDict.remove(predicateName);
        }
    }

    public void assignRules(String predicateName, Rule rule){
        /*
        @desc: To insert the value of the predicate into the rules dictionary
        @param: predicateName - string which stores the predicate name, rule - a Rule object which contains the values of the predicate
        @pre: None
        @post: the value of predicate are added into rules dictionary
        */
        if (rulesDict.containsKey(predicateName)){
            rulesDict.put(predicateName,rule);
        }
    }

    public void addRules(String predicateName, List<String> rules){
        /*
        @desc: To insert the value of the predicate into the rules dictionary
        @param: predicateName - string which stores the predicate name, rule - a list of strings which contains the values of the predicate
        @pre: None
        @post: the value of predicate are added into rules dictionary
        */
        if(rulesDict.containsKey(predicateName)){
            rulesDict.get(predicateName).addRules(rules);
        }
    }

    public Boolean containsOperator(String expression){
        /*
        @desc: To check if the string contains an operator
        @param: expression - string which stores the expression
        @pre: None
        @post: a boolean is returned
        */
        String[] operators = new String[] {"+","-","/","*","<",">",">=","<=","=="};
        for (String op: operators) {
            if (expression.contains(op)){
                return true;
            }
        }
        return false;
    }

    public String scan(String predicate, String objectKey, Integer queryCount){
        /*
        @desc: To search a target value from the predicate value from the rules dictionary
        @param: predicateName - string which stores the predicate name, objectKey - string used as a KEY constant, queryCount - integer which store the count of predicate search
        @pre: None
        @post: the value of target search is returned
        */
        if (rulesDict.containsKey(predicate)){
            List<List<String>> rulesObject = rulesDict.get(predicate).getValuePair();
            if (queryCount < rulesObject.size()){
                if (objectKey == null){
                    return rulesObject.get(queryCount).get(0);
                }
                else{
                    if (rulesObject.get(queryCount).get(1).equals(objectKey)){
                        return rulesObject.get(queryCount).get(0);
                    }
                }
            }
            else{
                return null;
            }
        }
        return null;
    }

    public Boolean checks(String predicate, List<String> queryObject){
        /*
        @desc: Performs a query search and returns a boolean if the query mentioned matches the rules from the rules dictionary
        @param: predicate - string which stores the predicate name, queryObject - list of string which stores the predicate argument
        @pre: None
        @post: a boolean is returned
        */
        List<String> rulesObject;
        Integer matchNo = 0;
        DeclaredVariables varList = new DeclaredVariables();
        Boolean rejected = false;
        Boolean result = false;
        if (this.rulesDict.containsKey(predicate)) {
            if (queryObject.size() == 0) {
                rulesObject = this.rulesDict.get(predicate).getValue();
                for (String rule : rulesObject) {
                    if (rule.contains("/*") || rule.contains("*/")) {
                        int start = rule.indexOf("/*");
                        int end = rule.indexOf("*/");
                        if (start != -1 && end != -1) {
                            rule = rule.substring(end + 2, rule.length());
                        }
                    }
                    if (rule.contains("write")) {
                        String content = rule.replace("write('", "");
                        content = content.replace("')", "");
                        System.out.print(content);
                    }
                    else if (rule.contains("read")) {
                        String content = rule.replace("read(", "");
                        content = content.replace(")", "");
                        String variableName = content;
                        Scanner input = new Scanner(System.in);
                        Integer value = Integer.parseInt(input.next());
                        varList.declareVariable(variableName);
                        varList.assgnVariable(variableName, value);
                    }
                    else {
                        String[] expression = rule.split(" ");
                        Integer opCount = 0;
                        for (String exp:expression) {
                            if (containsOperator(exp)){
                                opCount += 1;
                            }
                        }
                        if (opCount == 1) {
                            Expression exp = new Expression(varList.getValue(expression[0]), expression[1], varList.getValue(expression[2]));
                            result = exp.getResult();
                        } else if (opCount == 2) {
                            Expression subExp = new Expression(varList.getValue(Character.toString(expression[2].charAt(0))), Character.toString(expression[2].charAt(1)), varList.getValue(Character.toString(expression[2].charAt(2))));
                            Expression exp = new Expression(varList.getValue(expression[0]), expression[1], subExp);
                            result = exp.getResult();
                        }
                        if (!result) {
                            rejected = true;
                        }
                    }
                }
                if (!rejected){
                    return true;
                }
            }
            else{
                List<List<String>> rulesObject2dList = this.rulesDict.get(predicate).getValuePair();
                for (List<String> rules: rulesObject2dList) {
                    if (queryObject.size() == rules.size()){
                        for (int i = 0; i < queryObject.size(); i++){
                            if (queryObject.get(i).equals(rules.get(i))){
                                matchNo += 1;
                            }
                        }
                    }
                    if (matchNo == rules.size() && rules.size() != 0){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
