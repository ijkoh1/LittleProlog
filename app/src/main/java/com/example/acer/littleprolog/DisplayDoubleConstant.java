package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Seong Han on 10/3/2016.
 */

public class DisplayDoubleConstant extends RelativeLayout{

    private EditText pred_box2,edit_box1,edit_box2,edit_box3;
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

        setBackgroundColor(getResources().getColor(R.color.predicateColor));

        int padding_5dp = (int)getResources().getDimension(R.dimen.operators_padding);
        setPadding(padding_5dp,padding_5dp,padding_5dp,padding_5dp);

        this.edit_box1 = (EditText)findViewById(R.id.editConst1);
        this.edit_box2 = (EditText)findViewById(R.id.editConst2);
        this.edit_box3 = (EditText) findViewById(R.id.editConst3);
        this.pred_box2 = (EditText) findViewById(R.id.pred2);
        this.open = (TextView)findViewById(R.id.open_bracket_sign);
        this.close = (TextView)findViewById(R.id.close_bracket_sign);
        this.comma = (TextView)findViewById(R.id.comma_sign);
    }


}
