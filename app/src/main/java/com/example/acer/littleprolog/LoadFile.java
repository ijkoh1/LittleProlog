package com.example.acer.littleprolog;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Created by Acer on 14/10/2016.
 */

public class LoadFile {
    public TmpData loadFile(String fileName, MainActivity context){
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
}
