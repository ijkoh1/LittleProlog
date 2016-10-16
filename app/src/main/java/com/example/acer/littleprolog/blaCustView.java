package com.example.acer.littleprolog;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Seong Han on 14-Oct-16.
 */

public class blaCustView extends LinearLayout{

    //Initialize variable
    private TextView add;

    public blaCustView(Context context){
        super(context);
        init();
    }

    private void init() {

        //show double predicate block
        inflate(getContext(),R.layout.bla,this);

    }

}
