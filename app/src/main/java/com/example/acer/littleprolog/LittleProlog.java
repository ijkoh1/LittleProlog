package com.example.acer.littleprolog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Acer on 30/9/2016.
 */

public class LittleProlog {
    private Rules rules;
    private Integer queryCount;
    private ReadRules readRules;
    private String answer;
    public LittleProlog(String fileName){
        readRules = new ReadRules(fileName);
        queryCount = 0;
        rules = readRules.read(rules);
    }

    public void run(){
        String predicate = null;
        List<String> objects = new ArrayList<>();
        Boolean twoParameters = false;
        Boolean specialQuery = false;
        while (!this.answer.equals("done")){

        }
    }
}
