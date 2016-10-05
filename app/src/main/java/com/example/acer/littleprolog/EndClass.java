/*  @AUTHOR: SEONG HAN
*   @DATE CREATED: 3 OCT 2016
*   @DATE MODIFIED: 5 OCT 2016
*   @PURPOSE: This is the custom view class for user to specify which is the end of the statement for rules.
* */

package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EndClass extends RelativeLayout{

    //Initialize variable
    private TextView end_btn;

    public EndClass(Context context){
        super(context);
        init();
    }

    private void init() {

        //display end box
        inflate(getContext(),R.layout.end,this);

        //get content of the box
        this.end_btn = (TextView) findViewById(R.id.end_symbol);
    }


}
