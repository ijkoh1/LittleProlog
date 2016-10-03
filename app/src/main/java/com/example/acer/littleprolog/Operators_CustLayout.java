package com.example.acer.littleprolog;

/**
 * Created by Seong Han on 10/2/2016.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Operators_CustLayout extends RelativeLayout {

    private EditText edit_box1,edit_box2;
    private TextView more_eq_sign,less_eq_sign,more_than_sign,less_than_sign,
            eq,add,minus,mult,divide;

    public Operators_CustLayout(Context context){
        super(context);
        init();
    }

    public Operators_CustLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public Operators_CustLayout(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    private void init() {

        inflate(getContext(),R.layout.more_equal,this);
        inflate(getContext(),R.layout.less_equal,this);
        inflate(getContext(),R.layout.less_than,this);
        inflate(getContext(),R.layout.more_than,this);
        inflate(getContext(),R.layout.equal,this);
        inflate(getContext(),R.layout.add,this);
        inflate(getContext(),R.layout.minus,this);
        inflate(getContext(),R.layout.multiply,this);
        inflate(getContext(),R.layout.divide,this);

        setBackgroundColor(getResources().getColor(R.color.operatorsColor));

        int padding_5dp = (int)getResources().getDimension(R.dimen.operators_padding);
        setPadding(padding_5dp,padding_5dp,padding_5dp,padding_5dp);

        this.edit_box1 = (EditText)findViewById(R.id.empty1);
        this.edit_box2 = (EditText)findViewById(R.id.empty2);
        this.more_eq_sign = (TextView)findViewById(R.id.more_eq);
        this.less_eq_sign = (TextView)findViewById(R.id.less_eq);
        this.more_than_sign = (TextView)findViewById(R.id.more_sign);
        this.less_than_sign = (TextView)findViewById(R.id.less_sign);
        this.more_eq_sign = (TextView)findViewById(R.id.more_eq);
        this.eq = (TextView)findViewById(R.id.eq_sign);
        this.add = (TextView)findViewById(R.id.add_sign);
        this.minus = (TextView)findViewById(R.id.minus_sign);
        this.mult = (TextView)findViewById(R.id.mult_sign);
        this.divide = (TextView)findViewById(R.id.div_sign);
    }


}
