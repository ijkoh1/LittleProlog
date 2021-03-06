package com.example.acer.littleprolog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ivan on 28/9/2016.
 * Purpose: To read the rules input from a txt file
 */

public class ReadRules {
    //A buffer to read the files line by line
    private BufferedReader br = null;
    public ReadRules(String fileName){
        /*
        @desc: To initialise the values of the filename and the buffer to scan line by line from the txt file
        @param: None
        @pre: None
        @post: the values are initialised
        */
        try{
            br = new BufferedReader(new FileReader("C:\\Users\\Acer\\Documents\\Monash\\Year 3\\Sem 2\\FIT 3140\\Asgn1\\" + fileName + ".pl"));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public Rules read(Rules rules){
        /*
        @desc: To read the rules from the text file and store into the rules dictionary. Updates the rules dictionary
        @param: None
        @pre: Rules - A Rules object which contains the rules dictionary
        @post: the rules dictionary are updated
        */
        String predicate = null;
        String ruleString = "";
        Boolean makeFacts = false;
        String line;
        try{
            while ((line = br.readLine()) != null) {
                List<String> ruleParameter = new ArrayList<String>();
                line = line.replaceAll("\n","");
                if (line.contains(":-")){
                    makeFacts = true;
                    String[] split = line.split(":- ");
                    predicate = split[0];
                    line = split[1];
                }
                if (!makeFacts){
                    line = line.replaceAll(" ","");
                    if (line.contains("(") || line.contains(")")){
                        int start = line.indexOf("(");
                        int end = line.indexOf(")");
                        if (start != -1 && end != -1){
                            predicate = line.substring(0,start);
                            String object = line.substring(start+1,end);
                            object = object.replaceAll(" ","");
                            String[] objects = object.split(",");
                            for (int i = 0; i < objects.length; i++){
                                ruleParameter.add(objects[i]);
                            }
                        }
                    }
                    if (line.contains(".")){
                        if (rules.getHash().get(predicate) == null){
                            List<List<String>> rule2dList = new ArrayList<List<String>>();
                            rule2dList.add(ruleParameter);
                            Rule newRule = new RuleFacts(predicate,rule2dList);
                            rules.declaredRules(predicate);
                            rules.assignRules(predicate,newRule);
                        }
                        else{
                            rules.addRules(predicate,ruleParameter);
                        }
                        predicate = "";
                    }
                }
                else if (line.endsWith(".")){
                    ruleString += line.substring(0,line.length()-1);
                    String[] ruleList = ruleString.split(",");
                    rules.declaredRules(predicate);
                    for (String rule : ruleList) {
                        ruleParameter.add(rule);
                    }
                    Rule makeRule = new MakeFacts(predicate,ruleParameter);
                    rules.assignRules(predicate,makeRule);
                }
                else{
                    ruleString += line;
                }
            }
            return rules;
        }
        catch (IOException e){
            return null;
        }
    }
}