package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Seong Han on 10/3/2016.
 */

public class WriteClass extends RelativeLayout{

    private EditText write_box;
    private TextView open_wr,close_wr,write_sign;

    public WriteClass(Context context){
        super(context);
        init();
    }

    public WriteClass(Context context, AttributeSet attrs){
        super(context,attrs);
        init();
    }

    public WriteClass(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init();
    }

    private void init() {

        inflate(getContext(),R.layout.write,this);

        this.write_box = (EditText) findViewById(R.id.editWrite);
        this.write_sign = (TextView)findViewById(R.id.write_sign);
        this.open_wr = (TextView)findViewById(R.id.open_bracket_sign_wr);
        this.close_wr = (TextView)findViewById(R.id.close_bracket_sign_wr);
    }


}
