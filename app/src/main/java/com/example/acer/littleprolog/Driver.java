package com.example.acer.littleprolog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Acer on 28/9/2016.
 */

public class Driver {
    public static void main(String[] args){
        new Driver().callBack2();
    }

    public void callBack(){
        ReadRules fileRead = new ReadRules("example1");
        Rules rules = new Rules();
        rules = fileRead.read(rules);
        System.out.println(rules.getHash().get("animal").getValuePair());
        List<String> a = new ArrayList<String>();
        a.add("lion");
        System.out.println(rules.checks("animal", a));
    }

    public void callBack2(){
        Rules rules = new Rules();
        ReadRules fileRead = new ReadRules("example1");
        rules = fileRead.read(rules);
        LittleProlog lp = new LittleProlog(rules);
    }

    public void callBack3(){
        Rules rules = new Rules();
        ReadRules fileRead = new ReadRules("example1");
        rules = fileRead.read(rules);
        LittleProlog lp = new LittleProlog(rules);
        lp.run();
    }
}
