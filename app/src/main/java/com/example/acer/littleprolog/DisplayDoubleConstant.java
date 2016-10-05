/*  @AUTHOR: SEONG HAN
*   @DATE CREATED: 3 OCT 2016
*   @DATE MODIFIED: 5 OCT 2016
*   @PURPOSE: This is the custom view class to display box for users to insert 1 predicates
*               and 2 constants.
* */

package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DisplayDoubleConstant extends RelativeLayout{

    private EditText pred_box2,edit_box2,edit_box3;
    private TextView open,close,comma;

    public DisplayDoubleConstant(Context context){
        super(context);
        init();
    }

    public DisplayDoubleConstant(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public DisplayDoubleConstant(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    private void init() {

        inflate(getContext(),R.layout.double_pred,this);

        this.edit_box2 = (EditText)findViewById(R.id.editConst2);
        this.edit_box3 = (EditText) findViewById(R.id.editConst3);
        this.pred_box2 = (EditText) findViewById(R.id.pred2);
        this.open = (TextView)findViewById(R.id.open_bracket_sign);
        this.close = (TextView)findViewById(R.id.close_bracket_sign);
        this.comma = (TextView)findViewById(R.id.comma_sign);
    }

    public EditText getPredicate(){
        return this.pred_box2;
    }

    public EditText getConstant(){
        return this.edit_box2;
    }

    public EditText getConstant2(){
        return this.edit_box3;
    }
}
