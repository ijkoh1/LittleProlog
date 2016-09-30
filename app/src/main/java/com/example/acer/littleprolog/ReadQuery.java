package com.example.acer.littleprolog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 29/9/2016.
 */

public class ReadQuery {
    private String answer;
    private String predicate;
    private List<String> objects;
    public ReadQuery(String answer){
        this.answer = answer;
        this.predicate = "";
        this.objects = new ArrayList<>();
        this.extract();
    }

    private void extract(){
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

    public String getPredicate(){
        return this.predicate;
    }

    public List<String> getObjects(){
        return this.objects;
    }
}
