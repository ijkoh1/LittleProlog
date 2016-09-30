package com.example.acer.littleprolog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Acer on 28/9/2016.
 */

public class Driver {
    public static void main(String[] args){
        new Driver().callBack();
    }

    public void callBack(){
        ReadRules fileRead = new ReadRules("example1");
        Rules rules = new Rules();
        rules = fileRead.read(rules);
        System.out.println(rules.getHash().get("animal").getValuePair());
        List<List<String>> bla = new ArrayList<List<String>>();
        List<String> a = new ArrayList<String>();
        a.add("lion");
        bla.add(a);
        System.out.println(rules.checks("animal", a));
    }
}
