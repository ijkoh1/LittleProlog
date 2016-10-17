package com.example.acer.littleprolog;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 14/10/2016.
 */

public class LoadFile {
    public TmpData program(String fileName, MainActivity context){
        try{
//            FileInputStream fin = new FileInputStream(fileName + ".ser");
            FileInputStream fin = context.openFileInput(fileName+".ser");
            ObjectInputStream in = new ObjectInputStream(fin);
            TmpData newData = null;
            newData = (TmpData) in.readObject();
            in.close();
            fin.close();
            return newData;
        }
        catch (Exception e){
            return null;
        }
    }

    public Rules prolog(String fileName, MainActivity context){
        try{
//            FileInputStream fin = new FileInputStream(fileName + ".ser");
            FileInputStream fin = context.openFileInput(fileName+".pl");
            InputStreamReader inputReader = new InputStreamReader(fin);
            BufferedReader br = new BufferedReader(inputReader);
            Rules rules = new Rules();
            String line;
            String predicate = null;
            String ruleString = "";
            Boolean makeFacts = false;
            while ((line = br.readLine()) != null){
                System.out.println(line);
                line = line.replaceAll("\n","");
                List<String> ruleParameter = new ArrayList<String>();
                if (line.contains(":-")){
                    makeFacts = true;
                    String[] split = line.split(":-");
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
                            System.out.println(predicate);
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
            fin.close();
            return rules;
        }
        catch (Exception e){
            return null;
        }
    }
}
