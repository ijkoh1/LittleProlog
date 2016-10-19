/*  @AUTHOR: SEONG HAN
*   @DATE CREATED: 16 OCT 2016
*   @DATE MODIFIED: 19 OCT 2016
*   @PURPOSE: This is the custom view class to display more than equal to operator box.
* */

package com.example.acer.littleprolog;

import android.content.Context;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MoreEq_CustView extends RelativeLayout{

    //Initialize variable
    private EditText edit_box1,edit_box2;
    private TextView more_eq;

    public MoreEq_CustView(Context context){
        super(context);
        init();
    }

    private void init() {

        //show double predicate block
        inflate(getContext(),R.layout.more_equal,this);

        //get all the contents of the block
        this.edit_box1 = (EditText)findViewById(R.id.empty1);
        this.edit_box2 = (EditText) findViewById(R.id.empty2);
        this.more_eq = (TextView) findViewById(R.id.more_eq_sign);
    }

}
