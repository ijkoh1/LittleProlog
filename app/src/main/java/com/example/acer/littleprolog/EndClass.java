/*  @AUTHOR: SEONG HAN
*   @DATE CREATED: 3 OCT 2016
*   @DATE MODIFIED: 5 OCT 2016
*   @PURPOSE: This is the custom view class for user to specify which is the end of the statement for rules.
* */

package com.example.acer.littleprolog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
