/*  @AUTHOR: SEONG HAN
*   @DATE CREATED: 2 OCT 2016
*   @DATE MODIFIED: 5 OCT 2016
*   @PURPOSE: This is just a draft, there is not functionalities in this file.
* */

package com.example.acer.littleprolog;

import android.content.Context;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.view.MenuItem;


public class PopUp_Menu extends LinearLayout {

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.delete_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(DeleteButton.this, view);
                popupMenu.setOnMenuItemClickListener(DeleteButton.this);
                popupMenu.inflate(R.menu.delete_popup_menu);
                popupMenu.show();
            }
        });
    }*/

    private Button deleteButton, openButton, toolsButton;

    public PopUp_Menu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.deleteButton = (Button) findViewById(R.id.clear_button);
        this.openButton = (Button) findViewById(R.id.open_button);
        this.toolsButton = (Button) findViewById(R.id.tools_button);
    }
}

   /* public boolean onMenuItemClick(MenuItem item) {

        final ScrollView editorBox = (ScrollView) findViewById(R.id.editor1);
        final ScrollView consoleBox = (ScrollView) findViewById(R.id.console);

        switch (item.getItemId()) {
            case R.id.deleteEditor:
                if((editorBox).getChildCount() > 0)
                    (editorBox).removeAllViews();
                return true;

            case R.id.deleteConsole:
                if((consoleBox).getChildCount() > 0)
                    (consoleBox).removeAllViews();
                return true;

            case R.id.deleteAll:

                if((editorBox).getChildCount() > 0)
                    (editorBox).removeAllViews();

                if((consoleBox).getChildCount() > 0)
                    (consoleBox).removeAllViews();

                return true;
        }
        return true;
    }
}*/