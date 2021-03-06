package com.example.acer.littleprolog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Character.isDigit;
import static java.lang.Character.isUpperCase;

/*  @AUTHOR: IVAN KOH
*   @DATE CREATED: 29 SEPT 2016
*   @DATE MODIFIED: 5 OCT 2016
*   @status: "Prototype"
*   @version: 1
* */

/**
 * @author: Ivan
 * @purpose: To have the basic implementation of little prolog such as running query request from the user
 * */
public class LittleProlog {
    private Rules rules;
    private Integer queryCount;
    private String answer;
    public LittleProlog(Rules rulesInput){
        queryCount = 0;
        this.rules = rulesInput;
        this.answer = "";
    }

    /*
    @desc: Asks users for query
    @param: None
    @pre: None
    @post: A query is asked
    :return: User's input as an answer
    */
    public void query(){
        Scanner input = new Scanner(System.in);
        System.out.print("? - ");
        this.answer = input.next();
    }

    /*
    @desc: Runs the prolog class and accepts input and checks rules yes/no
    @param: None
    @pre: None
    @post: Runs functions of prolog
    :return: None
    */
    public String runQuery(String query, String predicate, Integer count){
        List<String> objects = new ArrayList<>();
        Boolean twoParameters = false;
        Boolean specialQuery = false;
        String output;
        if (query.equals(";")){
            if (twoParameters){
                output = this.rules.scan(predicate,objects.get(1), count);
            }
            else{
                output = this.rules.scan(predicate,null,count);
            }
            if (output == null){
                count = 0;
                specialQuery = false;
                return "\nNo";
            }
            else{
                this.queryCount += 1;
                specialQuery = true;
                return "\n" + output + ".";
            }
        }
        else{
            ReadQuery readQuery = new ReadQuery(query);
            predicate = readQuery.getPredicate();
            objects = readQuery.getObjects();
//            System.out.println(predicate + " " + objects);
            if (objects != null){
                if (predicate != null && objects.size() != 0){
                    if (!objects.get(0).contains(" ") && !isDigit(objects.get(0).charAt(0)) && isUpperCase(objects.get(0).charAt(0))){
                        if (objects.size() > 1){
                            output = this.rules.scan(predicate,objects.get(1),count);
                            twoParameters = true;
                        }
                        else{
                            output = this.rules.scan(predicate,null,count);
                            twoParameters = false;
                        }
                        if (output == null){
                            count = 0;
                            specialQuery = false;
                            return "\nNo";
                        }
                        else{
                            this.queryCount += 1;
                            specialQuery = true;
                            return "\n" + output + ".";
                        }
                    }
                    else{
                        if (this.rules.checks(predicate,objects)){
                            return "\nYes";
                        }
                        else{
                            return "\nNo";
                        }
                    }
                }
                else{
                    System.out.println(predicate);
                    if (this.rules.checks(predicate,objects)){
                        return "\nYes";
                    }
                    else{
                        return "\nNo";
                    }
                }
            }
        }
        return "";
    }
}
