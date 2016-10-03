package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Seong Han on 10/3/2016.
 */

public class ReadClass extends RelativeLayout{

    private EditText read_box;
    private TextView open_wr,close_wr,read_sign;

    public ReadClass(Context context){
        super(context);
        init();
    }

    public ReadClass(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public ReadClass(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    private void init() {

        inflate(getContext(),R.layout.read,this);

        setBackgroundColor(getResources().getColor(R.color.readColor));

        int padding_5dp = (int)getResources().getDimension(R.dimen.operators_padding);
        setPadding(padding_5dp,padding_5dp,padding_5dp,padding_5dp);

        this.read_box = (EditText) findViewById(R.id.editRead);
        this.read_sign = (TextView)findViewById(R.id.read_sign);
        this.open_wr = (TextView)findViewById(R.id.open_bracket_sign_wr);
        this.close_wr = (TextView)findViewById(R.id.close_bracket_sign_wr);
    }


}
