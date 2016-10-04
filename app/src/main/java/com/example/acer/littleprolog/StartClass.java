package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Seong Han on 10/3/2016.
 */

public class StartClass extends RelativeLayout{

    private TextView start_btn;
    private Button delete_btn;

    public StartClass(Context context){
        super(context);
        init();
    }

    public StartClass(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public StartClass(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    private void init() {

        inflate(getContext(),R.layout.start,this);

        this.start_btn = (TextView) findViewById(R.id.start_symbol);
    }


}
