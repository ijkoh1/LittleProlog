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

public class SaveFile {
    private TmpData ruledata;
    public SaveFile(TmpData prologData){
        this.ruledata = prologData;
    }

    public Boolean saveFile(String fileName, MainActivity context){
        try{
//            FileOutputStream fout = new FileOutputStream(fileName + ".ser");
            FileOutputStream fout = context.openFileOutput(fileName + ".ser", Context.MODE_PRIVATE);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(this.ruledata);
            out.close();
            fout.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
