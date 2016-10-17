package com.example.acer.littleprolog;

/**
 * Created by Acer on 10/10/2016.
 */
import android.content.Context;
import android.media.MediaScannerConnection;
import android.util.Log;
import android.content.Context;
import android.view.View;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SaveFile {
    private TmpData data;
    public SaveFile(TmpData prologData){
        this.data = prologData;
    }

    public Boolean saveProgram(String fileName, MainActivity context){
        try{
//            FileOutputStream fout = new FileOutputStream(fileName + ".ser");
            FileOutputStream fout = context.openFileOutput(fileName + ".ser", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(this.data);
            out.close();
            fout.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Boolean saveProlog(String fileName, MainActivity context){
        try{
//            FileOutputStream fout = new FileOutputStream(fileName + ".ser");
            FileOutputStream fout = context.openFileOutput(fileName + ".pl", Context.MODE_PRIVATE);
            String prologString = "";
            List<String> content = writeProlog();
            for (String line: content) {
                prologString += line + "\n";
            }
            System.out.println(prologString);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fout);
            outputStreamWriter.write(prologString);
            outputStreamWriter.close();
            fout.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    private List<String> writeProlog(){
        List<String> result = new ArrayList<>();
        HashMap<String, Rule> rules = this.data.getRules().getHash();
        Iterator it = rules.entrySet().iterator();
        while(it.hasNext()){
            String line = "";
            Map.Entry pair = (Map.Entry) it.next();
            String predicate = pair.getKey().toString();
            line += predicate + "(";
            if (pair.getValue() instanceof  RuleFacts){
                for (List<String> objectValue: ((RuleFacts) pair.getValue()).getValuePair()){
                    String subline = "";
                    if (objectValue.size() == 1){
                        subline += line + objectValue.get(0) + ").";
                    }
                    else if (objectValue.size() == 2){
                        subline += line + objectValue.get(0) + "," + objectValue.get(1) + ").";
                    }
                    result.add(subline);
                }
            }
            else if (pair.getValue() instanceof MakeFacts){
                result.add(predicate + ":-");
                for (int i = 0; i < ((MakeFacts) pair.getValue()).getValue().size(); i++){
                    String rule = ((MakeFacts) pair.getValue()).getValue().get(i);
                    if (i == ((MakeFacts) pair.getValue()).getValue().size()-1){
                        result.add(rule + '.');
                    }
                    else{
                        result.add(rule + ",");
                    }
                }
            }
        }
        System.out.println(result);
        return result;
    }
}
