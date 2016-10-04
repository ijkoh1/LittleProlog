package com.example.acer.littleprolog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private LinearLayout blockContainer,editorBox1,consoleBox;
    private EditText editorBox2;
    private Button clear_btn,open_btn,run_btn,save_btn,tools_btn,delete_btn,single_const_btn,
            double_const_btn,write_btn,read_btn,operator_btn,start_btn,end_btn;
    private View selectedView;

    //set public class for long click
    public class selectLongClick implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View view) {
            view.setBackgroundColor(getResources().getColor(R.color.highlightColor));
            selectedView = view;
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initiate linear layout
        editorBox1 = (LinearLayout) findViewById(R.id.editor1_linlay);
        consoleBox = (LinearLayout) findViewById(R.id.console_linlay);

        //initiate edit text
        editorBox2 = (EditText) findViewById(R.id.editor2_edit) ;

        //initiate buttons
        clear_btn = (Button)findViewById(R.id.clear_button);
        open_btn = (Button)findViewById(R.id.open_button);
        run_btn = (Button)findViewById(R.id.run_button);
        save_btn = (Button)findViewById(R.id.save_button);
        tools_btn = (Button)findViewById(R.id.tools_button);
        delete_btn = (Button)findViewById(R.id.delete_button);
        single_const_btn = (Button)findViewById(R.id.single_constant_button);
        double_const_btn = (Button)findViewById(R.id.double_constant_button);
        write_btn = (Button)findViewById(R.id.write_button);
        read_btn = (Button)findViewById(R.id.read_button);
        operator_btn = (Button)findViewById(R.id.operator_button);
        start_btn = (Button)findViewById(R.id.start_button) ;
        end_btn = (Button)findViewById(R.id.end_button);

        editorBox2.setText("?- ");

        selectedView = null;

        //on click listener for clear button
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.deleteEditor1:
                                if((editorBox1).getChildCount() > 0)
                                (editorBox1).removeAllViews();
                                return true;

                            case R.id.deleteEditor2:
                                editorBox2.setText("?- ");
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
        open_btn.setOnClickListener(new View.OnClickListener() {
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

        //listener for run button
        run_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.run_rules_facts:
                                //functions goes here
                                return true;

                            case R.id.run_query:
                                //funtion goes here
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.run_popup_menu);
                popupMenu.show();
            }
        });

        //listener for delete button
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedView != null) {
                    editorBox1.removeView(selectedView);
                }

                else{
                    Toast.makeText(getApplicationContext(), "Please select block to delete",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //listener for tools button
        tools_btn.setOnClickListener(new View.OnClickListener() {
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

        //listener for start button
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StartClass startButton= new StartClass(MainActivity.this);
                startButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(startButton);

            }
        });

        //listener for end button
        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EndClass endButton= new EndClass(MainActivity.this);
                endButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(endButton);
            }
        });

        //listener for single constant button
        single_const_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DisplaySingleConstant singleConstButton = new DisplaySingleConstant(MainActivity.this);
                singleConstButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(singleConstButton);
            }
        });

        //listener for double constant button
        double_const_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DisplayDoubleConstant doubleConstButton= new DisplayDoubleConstant(MainActivity.this);
                doubleConstButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(doubleConstButton);
            }
        });


        //listener for write button
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final WriteClass writeButton= new WriteClass(MainActivity.this);
                writeButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(writeButton);
            }
        });

        //listener for read button
        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ReadClass readButton= new ReadClass(MainActivity.this);
                readButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(readButton);
            }
        });

        operator_btn.setOnClickListener(new View.OnClickListener() {
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

    public void checkQueryLine(){

    }
}


