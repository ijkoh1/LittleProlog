package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Seong Han on 10/3/2016.
 */

public class EndClass extends RelativeLayout{

    private TextView end_btn;

    public EndClass(Context context){
        super(context);
        init();
    }

    public EndClass(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public EndClass(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    private void init() {

        inflate(getContext(),R.layout.end,this);

        this.end_btn = (TextView) findViewById(R.id.end_symbol);
    }


}
