package com.example.acer.littleprolog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 29/9/2016.
 * Purpose: To read the query which the user entered
 */


public class ReadQuery {
    //A string which stores the answer
    private String answer;
    //A string which stores the name of predicate
    private String predicate;
    //A list of strings which stores the argument of the predicate
    private List<String> objects;
    public ReadQuery(String answer){
        /*
        @desc: To initialise the values of the answer , predicate name and object value
        @param: None
        @pre: None
        @post: the values are initialised
        */
        this.answer = answer;
        this.predicate = "";
        this.objects = new ArrayList<>();
        this.extract();
    }

    private void extract(){
        /*
        @desc: To extract the predicate name and predicate value from the answer string
        @param: None
        @pre: None
        @post: the predicate name and values are extracted
        */
        if (this.answer.contains("(") || this.answer.contains(")")){
            Integer start = this.answer.indexOf("(");
            Integer end = this.answer.indexOf(")");
            if (start != 1 && end != -1){
                this.predicate = this.answer.substring(0,start);
                String object = this.answer.substring(start+1,end);
                String[] tmpObjects = object.split(",");
                for (String obj: tmpObjects) {
                    this.objects.add(obj);
                }
            }
        }
        if (this.answer.charAt(this.answer.length()-1) == '.'){
            if (this.predicate.equals("")){
                this.predicate = this.answer.substring(0,this.answer.length()-1);
            }
        }
    }

    public String getPredicate() {
        /*
        @desc: To retrieve the value of the predicate name
        @param: None
        @pre: None
        @post: the values are returned
        */
        return this.predicate;
    }

    public List<String> getObjects(){
        /*
        @desc: To retrieve the value of the predicate argument
        @param: None
        @pre: None
        @post: the values are returned
        */
        return this.objects;
    }
}
