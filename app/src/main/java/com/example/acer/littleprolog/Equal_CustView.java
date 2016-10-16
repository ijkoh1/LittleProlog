package com.example.acer.littleprolog;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Seong Han on 14-Oct-16.
 */

public class Equal_CustView extends LinearLayout{

    //Initialize variable
    private EditText edit_box1,edit_box2;
    private TextView equal;

    public Equal_CustView(Context context){
        super(context);
        init();
    }

    private void init() {

        //show double predicate block
        inflate(getContext(),R.layout.equal,this);

        //get all the contents of the block
        this.edit_box1 = (EditText)findViewById(R.id.empty1);
        this.edit_box2 = (EditText) findViewById(R.id.empty2);
        this.equal = (TextView) findViewById(R.id.eq_sign);
    }

}
