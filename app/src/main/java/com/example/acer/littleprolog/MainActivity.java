package com.example.acer.littleprolog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private LinearLayout blockContainer,editorBox1,consoleBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editorBox1 = (LinearLayout) findViewById(R.id.editor1_linlay);
        consoleBox = (LinearLayout) findViewById(R.id.console_linlay);

        //on click listener for clear button
        findViewById(R.id.clear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.deleteEditor:
                                if((editorBox1).getChildCount() > 0)
                                (editorBox1).removeAllViews();
                                return true;

                            case R.id.deleteConsole:
                                if((consoleBox).getChildCount() > 0)
                                (consoleBox).removeAllViews();
                                return true;

                            case R.id.deleteAll:

                                if((editorBox1).getChildCount() > 0)
                                (editorBox1).removeAllViews();

                                if((consoleBox).getChildCount() > 0)
                                    (consoleBox).removeAllViews();
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.delete_popup_menu);
                popupMenu.show();


            }
        });

        //listener for open button
        findViewById(R.id.open_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //insert function
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.open_popup_menu);
                popupMenu.show();
            }
        });

        //listener for delete button
        findViewById(R.id.delete_button).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editorBox1.removeView(view);
                return true;
            }
        });

        //listener for tools button
        findViewById(R.id.tools_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        //insert function
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.tools_popup_menu);
                popupMenu.show();
            }
        });

        //listener for single constant button
        findViewById(R.id.single_constant_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DisplaySingleConstant singleConstButton = new DisplaySingleConstant(MainActivity.this);
                editorBox1.addView(singleConstButton);
            }
        });

        //listener for double constant button
        findViewById(R.id.double_constant_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DisplayDoubleConstant doubleConstButton= new DisplayDoubleConstant(MainActivity.this);
                editorBox1.addView(doubleConstButton);
            }
        });

        //listener for write constant button
        findViewById(R.id.write_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DisplayDoubleConstant doubleConstButton= new DisplayDoubleConstant(MainActivity.this);
                editorBox1.addView(doubleConstButton);
            }
        });

        //listener for write button
        findViewById(R.id.write_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final WriteClass writeButton= new WriteClass(MainActivity.this);
                editorBox1.addView(writeButton);
            }
        });

        //listener for read button
        findViewById(R.id.read_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ReadClass readButton= new ReadClass(MainActivity.this);
                editorBox1.addView(readButton);
            }
        });

        findViewById(R.id.operator_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.equal:
                                return true;

                            case R.id.more_equal:
                                return true;

                            case R.id.less_equal:
                                return true;

                            case R.id.less_than:
                                return true;

                            case R.id.add:
                                return true;

                            case R.id.minus:
                                return true;

                            case R.id.mult:
                                return true;

                            case R.id.div:
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.operator_popup_menu);
                popupMenu.show();
            }
        });
    }


}


