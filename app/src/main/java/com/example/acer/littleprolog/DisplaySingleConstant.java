package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Seong Han on 10/3/2016.
 */

public class DisplaySingleConstant extends RelativeLayout{

    private EditText pred_box1,edit_box1,edit_box2;
    private TextView open,close;

    public DisplaySingleConstant(Context context){
        super(context);
        init();
    }

    public DisplaySingleConstant(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public DisplaySingleConstant(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    private void init() {

        inflate(getContext(),R.layout.single_pred,this);

        this.edit_box1 = (EditText)findViewById(R.id.editConst1);
        this.pred_box1 = (EditText) findViewById(R.id.pred1);
        this.open = (TextView)findViewById(R.id.open_bracket_sign);
        this.close = (TextView)findViewById(R.id.close_bracket_sign);
    }

    public EditText getPredicate(){
        return this.pred_box1;
    }

    public EditText getConstant(){
        return this.edit_box1;
    }

}
