/*  @AUTHOR: SEONG HAN
*   @DATE CREATED: 3 OCT 2016
*   @DATE MODIFIED: 5 OCT 2016
*   @PURPOSE: This is the custom view class to display read(' ') box.
* */

package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ReadClass extends RelativeLayout{

    //Initialize variables
    private EditText read_box;
    private TextView open_wr,close_wr,read_sign;

    public ReadClass(Context context){
        super(context);
        init();
    }


    private void init() {

        //display read block
        inflate(getContext(),R.layout.read,this);

        //get content of read block
        this.read_box = (EditText) findViewById(R.id.editRead);
        this.read_sign = (TextView)findViewById(R.id.read_sign);
        this.open_wr = (TextView)findViewById(R.id.open_bracket_sign_wr);
        this.close_wr = (TextView)findViewById(R.id.close_bracket_sign_wr);
    }


}
