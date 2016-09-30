package com.example.acer.littleprolog;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Acer on 28/9/2016.
 */

public class Rules {
    private HashMap<String, Rule> rulesDict;

    public Rules(){
        rulesDict = new HashMap<String, Rule>();
    }

    public boolean isRule(String predicateName){
        return rulesDict.containsKey(predicateName);
    }

    public HashMap<String,Rule> getHash(){
        return this.rulesDict;
    }

    public void clearHash(){
        rulesDict.clear();
    }

    public void declaredRules(String predicateName){
        if (!rulesDict.containsKey(predicateName)){
            rulesDict.put(predicateName,null);
        }
    }

    public void removeRule(String predicateName){
        if (rulesDict.containsKey(predicateName))
        {
            rulesDict.remove(predicateName);
        }
    }

    public void assignRules(String predicateName, Rule rule){
        if (rulesDict.containsKey(predicateName)){
            rulesDict.put(predicateName,rule);
        }
    }

    public void addRules(String predicateName, List<String> rules){
        if(rulesDict.containsKey(predicateName)){
            rulesDict.get(predicateName).addRules(rules);
        }
    }

    public Boolean containsOperator(String expression){
        String[] operators = new String[] {"+","-","/","*","<",">",">=","<=","=="};
        for (String op: operators) {
            if (expression.contains(op)){
                return true;
            }
        }
        return false;
    }

    public String scan(String predicate, String objectKey, Integer queryCount){
        if (rulesDict.containsKey(predicate)){
            List<String> rulesObject = rulesDict.get(predicate).getValue();
            if (queryCount < rulesDict.size()){
                if (objectKey == null){
                    return Character.toString(rulesObject.get(queryCount).charAt(0));
                }
                else{
                    if (Character.toString(rulesObject.get(queryCount).charAt(1)).equals(objectKey)){
                        return Character.toString(rulesObject.get(queryCount).charAt(0));
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
        List<String> rulesObject;
        Integer matchNo = 0;
        DeclaredVariables varList = new DeclaredVariables();
        Boolean rejected = false;
        Boolean result = false;
        if (this.rulesDict.containsKey(predicate)) {
            if (queryObject == null) {
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
                    } else if (rule.contains("read")) {
                        String content = rule.replace("read(", "");
                        content = content.replace(")", "");
                        String variableName = content;
                        Scanner input = new Scanner(System.in);
                        Integer value = Integer.parseInt(input.next());
                        varList.assgnVariable(variableName, value);
                    } else {
                        String[] expression = rule.split(" ");
                        if (expression.length == 3) {
                            Expression exp = new Expression(varList.getValue(expression[0]), expression[1], varList.getValue(expression[2]));
                            result = exp.getResult();
                        } else if (expression.length == 5) {
                            Expression subExp = new Expression(varList.getValue(expression[2]), expression[3], varList.getValue(expression[4]));
                            Expression exp = new Expression(varList.getValue(expression[0]), expression[1], subExp);
                            result = exp.getResult();
                        }
                    }
                    if (!result) {
                        rejected = true;
                    }
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
            if (queryObject == null && !rejected){
                return true;
            }
        }
        return false;
    }
}
