/*  @AUTHOR: SEONG HAN
*   @DATE CREATED: 3 OCT 2016
*   @DATE MODIFIED: 5 OCT 2016
*   @PURPOSE: This is the custom view class to display box for users to start the function to insert
*           facts and rules.
* */

package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class StartClass extends RelativeLayout{

    //initialize variable
    private EditText edit1;

    public StartClass(Context context){
        super(context);
        init();
    }


    private void init() {

        //display start block
        inflate(getContext(),R.layout.start,this);

        edit1.setText(R.string.start);

        //get content of start block
        this.edit1 = (EditText) findViewById(R.id.start_edittext);

    }


}
