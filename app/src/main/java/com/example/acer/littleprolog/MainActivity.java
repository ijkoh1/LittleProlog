/*  @AUTHOR: SEONG HAN & IVAN KOH
*   @DATE CREATED: 29 SEPT 2016
*   @DATE MODIFIED: 5 OCT 2016
*   @PURPOSE: This is the main activity class for the application
* */

package com.example.acer.littleprolog;

import android.app.Dialog;
import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //initialize global variable
    private LinearLayout blockContainer,editorBox1,consoleBox;
    private EditText editorBox2,eq_box2;
    private Button clear_btn,open_btn,run_btn,save_btn,metaData_btn,delete_btn,single_const_btn,
            double_const_btn,write_btn,read_btn,start_btn,end_btn,add_btn,minus_btn,mult_btn,div_btn,
            eq_btn,lt_btn,mt_btn,lt_eq_btn,mt_eq_btn;
    private View selectedView;
    private TextView console;
    private Rules currentRules = new Rules();
    private DeclaredVariables declaredVariables = new DeclaredVariables();
    private MetaData currentMetaData = null;
    String predicate = "";
    Integer count = 0;

    //set public class for long click (this will be called by delete button, so that when
    //user long press on the block, the delete button can delete the long-pressed item.
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
        console = (TextView) findViewById(R.id.console_text);
        //initiate edit text
        editorBox2 = (EditText) findViewById(R.id.editor2_edit) ;

        //initiate buttons
        clear_btn = (Button)findViewById(R.id.clear_button);
        open_btn = (Button)findViewById(R.id.open_button);
        run_btn = (Button)findViewById(R.id.run_button);
        save_btn = (Button)findViewById(R.id.save_button);
        metaData_btn = (Button)findViewById(R.id.metaData_button);
        delete_btn = (Button)findViewById(R.id.delete_button);
        single_const_btn = (Button)findViewById(R.id.single_constant_button);
        double_const_btn = (Button)findViewById(R.id.double_constant_button);
        write_btn = (Button)findViewById(R.id.write_button);
        read_btn = (Button)findViewById(R.id.read_button);
        start_btn = (Button)findViewById(R.id.start_button) ;
        end_btn = (Button)findViewById(R.id.end_button);
        eq_btn = (Button)findViewById(R.id.equal_btn);
        add_btn = (Button)findViewById(R.id.add_btn);
        minus_btn = (Button)findViewById(R.id.minus_btn);
        mult_btn = (Button)findViewById(R.id.mult_btn);
        div_btn = (Button)findViewById(R.id.div_btn);
        lt_eq_btn = (Button)findViewById(R.id.lt_eq_btn);
        lt_btn = (Button)findViewById(R.id.less_than_btn);
        mt_eq_btn = (Button)findViewById(R.id.mt_eq_btn);
        mt_btn = (Button)findViewById(R.id.more_than_btn);

        //set the initial text to editorBox2
        editorBox2.setText("?- ");

        //initiate on touch listener for operator custom view
        eq_btn.setOnTouchListener(new MyTouchListener());
        lt_btn.setOnTouchListener(new MyTouchListener());
        lt_eq_btn.setOnTouchListener(new MyTouchListener());
        mt_btn.setOnTouchListener(new MyTouchListener());
        mt_eq_btn.setOnTouchListener(new MyTouchListener());
        add_btn.setOnTouchListener(new MyTouchListener());
        minus_btn.setOnTouchListener(new MyTouchListener());
        mult_btn.setOnTouchListener(new MyTouchListener());
        div_btn.setOnTouchListener(new MyTouchListener());

        //initialize all operaton custom view
        Equal_CustView equalCView = new Equal_CustView(MainActivity.this);
        LessThan_CustView lessthanCView = new LessThan_CustView(MainActivity.this);
        LessEq_CustView lessEqCView = new LessEq_CustView(MainActivity.this);
        MoreThan_CustView morethanCView = new MoreThan_CustView(MainActivity.this);
        MoreEq_CustView moreEqCView = new MoreEq_CustView(MainActivity.this);
        Add_CustView addCView = new Add_CustView(MainActivity.this);
        Minus_CustView minusCView = new Minus_CustView(MainActivity.this);
        Multiply_CustView multCView = new Multiply_CustView(MainActivity.this);
        Divide_CustView divideCView = new Divide_CustView(MainActivity.this);

        //initiate on drag listener for operator custom view
        equalCView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
        lessEqCView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
        lessthanCView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
        moreEqCView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
        morethanCView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
        addCView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
        minusCView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
        multCView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
        divideCView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
        editorBox1.setOnDragListener(new MyDragListener());

        selectedView = null;

        //on click listener for clear button
        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);   //get PopupMenu class
                //set listener for menu, so that when user click on the button, popup menu will be shown
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            //when user tap "Clear read and facts"
                            case R.id.clearEditor1:
                                if((editorBox1).getChildCount() > 0)    //if there is child in editorBox1
                                (editorBox1).removeAllViews();
                                return true;

                            //when user tap "clear query"
                            case R.id.clearEditor2:
                                editorBox2.setText("?- ");
                                return true;

                            //when user tap "clear console"
                            case R.id.clearConsole:
                                console.setText("");
                                return true;

                            //when user tap "clear all"
                            case R.id.clearAll:

                                if((editorBox1).getChildCount() > 0)
                                (editorBox1).removeAllViews();

                                editorBox2.setText("?- ");

                                console.setText("");

                                return true;
                        }
                        return true;
                    }
                });

                //shows the popup menu
                popupMenu.inflate(R.menu.clear_popup_menu);
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
                        switch (item.getItemId()) {
                            case R.id.openProgram:
                                inflateOpenProgramPopUp();
                                return true;

                            case R.id.openProlog:
                                inflateOpenPrologPopUp();
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.open_popup_menu);
                popupMenu.show();
            }
        });

        //listener for open button
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.saveProgram:
                                inflateSaveProgramPopUp();
                                return true;

                            case R.id.saveProlog:
                                inflateSavePrologPopUp();
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.save_popup_menu);
                popupMenu.show();
            }
        });

        //listener for run button
        run_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view); //get PopupMenu class
                //set listener for menu, so that when user click on the button, popup menu will be shown
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {

                            //when user tap "run rule/facts"
                            case R.id.run_rules_facts:
                                MainActivity.this.currentRules = addPredicates();
                                if (MainActivity.this.currentRules == null){
                                    console.append("\nError: Wrong Rule input format");
                                }
                                return true;

                            //when user tap "run query"
                            case R.id.run_query:
                                checkQueryLine();
                                return true;
                        }
                        return true;
                    }
                });

                //display pop up menu of run
                popupMenu.inflate(R.menu.run_popup_menu);
                popupMenu.show();
            }
        });

        //listener for delete button
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if user has selected a box, then proceed to delete the box
                if (selectedView != null) {
                    editorBox1.removeView(selectedView);
                }

                //if user has not selected a box, show a toast message
                else if (selectedView == null){
                    Toast.makeText(getApplicationContext(), "Please select block to delete",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        //listener for tools button
        metaData_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);   //get PopupMenu class
                //set listener for menu, so that when user click on the button, popup menu will be shown
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.viewMetaData:
                                inflateViewMetaDataPopUp();
                                return true;
                            case R.id.editMetaData:
                                inflateEditMetaDataPopUp();
                                return true;
                        }
                        return true;
                    }
                });
                popupMenu.inflate(R.menu.metadata_popup_menu);
                popupMenu.show();
            }
        });

        //listener for start button
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final StartClass startButton= new StartClass(MainActivity.this);    //initiate StartClass custom view
                startButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(startButton);    //add start block into Insert Rules and Facts column
            }
        });

        //listener for end button
        end_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EndClass endButton= new EndClass(MainActivity.this);     //initiate EndClass custom view
                endButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(endButton);      //add end block into Insert Rules and Facts column
            }
        });

        //listener for single constant button
        single_const_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DisplaySingleConstant singleConstButton = new DisplaySingleConstant(MainActivity.this);   //initiate Single Constant Custom View
                singleConstButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(singleConstButton);      //add single constant block into Insert Rules and Facts column
            }
        });

        //listener for double constant button
        double_const_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DisplayDoubleConstant doubleConstButton= new DisplayDoubleConstant(MainActivity.this);       //Initiate double constant custom view
                doubleConstButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(doubleConstButton);         //add double constant block into Insert Rules and Facts column
            }
        });

        //listener for write button
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final WriteClass writeButton= new WriteClass(MainActivity.this);    //initiate write class custom view
                writeButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(writeButton);        //add write block into Insert Rules and Facts column
            }
        });

        //listener for read button
        read_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ReadClass readButton= new ReadClass(MainActivity.this);       //initiate read button custom view
                readButton.setOnLongClickListener(new selectLongClick());
                editorBox1.addView(readButton);     //add read block into Insert Rules and Facts column
            }
        });

    }

    //to set listener for draggable objects
    public class MyTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    public class MyDragListener implements View.OnDragListener {
//        Equal_CustView equalCView = new Equal_CustView(MainActivity.this);
//        LessThan_CustView lessthanCView = new LessThan_CustView(MainActivity.this);
//        LessEq_CustView lessEqCView = new LessEq_CustView(MainActivity.this);
//        MoreThan_CustView morethanCView = new MoreThan_CustView(MainActivity.this);
//        MoreEq_CustView moreEqCView = new MoreEq_CustView(MainActivity.this);
//        Add_CustView addCView = new Add_CustView(MainActivity.this);
//        Minus_CustView minusCView = new Minus_CustView(MainActivity.this);
//        Multiply_CustView multCView = new Multiply_CustView(MainActivity.this);
//        Divide_CustView divideCView = new Divide_CustView(MainActivity.this);

        //on drag listener to setup drop environment for draggable objects
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    View view = (View) event.getLocalState();

                    //ViewGroup owner = (ViewGroup) view.getParent();
                    //owner.removeView(view);
                    /*LinearLayout container = (LinearLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);*/
                    int eqID = eq_btn.getId();
                    int ltID = lt_btn.getId();
                    int ltEqID = lt_eq_btn.getId();
                    int gtID = mt_btn.getId();
                    int gtEqID = mt_eq_btn.getId();
                    int addID = add_btn.getId();
                    int minusID = minus_btn.getId();
                    int multID = mult_btn.getId();
                    int divID = div_btn.getId();

                    if (view.getId() == eqID){
                        Equal_CustView equal_custView = new Equal_CustView(MainActivity.this);
                        equal_custView.setOnTouchListener(new MyTouchListener());
                        equal_custView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
                        ViewGroup viewGroup = (ViewGroup) v;
                        viewGroup.addView(equal_custView);
                        break;
                    }

                    if (view.getId() == ltID){
                        LessThan_CustView lessThanView = new LessThan_CustView(MainActivity.this);
                        lessThanView.setOnTouchListener(new MyTouchListener());
                        lessThanView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
                        ViewGroup viewGroup = (ViewGroup) v;
                        viewGroup.addView(lessThanView);
                        break;
                    }

                    if (view.getId() == ltEqID){
                        LessEq_CustView lessThanEqView = new LessEq_CustView(MainActivity.this);
                        lessThanEqView.setOnTouchListener(new MyTouchListener());
                        lessThanEqView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
                        ViewGroup viewGroup = (ViewGroup) v;
                        viewGroup.addView(lessThanEqView);
                        break;
                    }


                    if (view.getId() == gtID){
                        MoreThan_CustView moreThanView = new MoreThan_CustView(MainActivity.this);
                        moreThanView.setOnTouchListener(new MyTouchListener());
                        moreThanView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
                        ViewGroup viewGroup = (ViewGroup) v;
                        viewGroup.addView(moreThanView);
                        break;
                    }


                    if (view.getId() == gtEqID){
                        MoreEq_CustView moreEq_custView = new MoreEq_CustView(MainActivity.this);
                        moreEq_custView.setOnTouchListener(new MyTouchListener());
                        moreEq_custView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
                        ViewGroup viewGroup = (ViewGroup) v;
                        viewGroup.addView(moreEq_custView);
                        break;
                    }


                    if (view.getId() == addID){
                        Add_CustView add_custView = new Add_CustView(MainActivity.this);
                        add_custView.setOnTouchListener(new MyTouchListener());
                        add_custView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
                        ViewGroup viewGroup = (ViewGroup) v;
                        viewGroup.addView(add_custView);
                        break;
                    }


                    if (view.getId() == minusID){
                        Minus_CustView minusView = new Minus_CustView(MainActivity.this);
                        minusView.setOnTouchListener(new MyTouchListener());
                        minusView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
                        ViewGroup viewGroup = (ViewGroup) v;
                        viewGroup.addView(minusView);
                        break;
                    }


                    if (view.getId() == multID){
                        Multiply_CustView multiply_custView = new Multiply_CustView(MainActivity.this);
                        multiply_custView.setOnTouchListener(new MyTouchListener());
                        multiply_custView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
                        ViewGroup viewGroup = (ViewGroup) v;
                        viewGroup.addView(multiply_custView);
                        break;
                    }


                    if (view.getId() == divID){
                        Divide_CustView divide_custView = new Divide_CustView(MainActivity.this);
                        divide_custView.setOnTouchListener(new MyTouchListener());
                        divide_custView.findViewById(R.id.empty2).setOnDragListener(new MyDragListener());
                        ViewGroup viewGroup = (ViewGroup) v;
                        viewGroup.addView(divide_custView);
                        break;
                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    public void inflateViewMetaDataPopUp(){
        final View metaDataPopUpMenu = getLayoutInflater().inflate(R.layout.view_metadata_popup,null);
        final Dialog dialogWindow = new Dialog(this);
        TextView authorTextBox = (TextView) metaDataPopUpMenu.findViewById(R.id.author_textBox);
        TextView emailTextBox = (TextView) metaDataPopUpMenu.findViewById(R.id.email_textBox);
        TextView descriptionTextBox = (TextView) metaDataPopUpMenu.findViewById(R.id.description_textBox);
        if (currentMetaData != null){
            authorTextBox.setText(currentMetaData.getAuthor());
            emailTextBox.setText(currentMetaData.getEmail());
            descriptionTextBox.setText(currentMetaData.getDescription());
        }
        Button doneButton = (Button) metaDataPopUpMenu.findViewById(R.id.metaDataDone_button);
        doneButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialogWindow.dismiss();
            }
        });
        dialogWindow.setTitle("MetaData Information");
        dialogWindow.setContentView(metaDataPopUpMenu);
        dialogWindow.show();
    }

    public void inflateEditMetaDataPopUp(){
        final View metaDataPopUpMenu = getLayoutInflater().inflate(R.layout.edit_metadata_popup,null);
        final Dialog dialogWindow = new Dialog(this);
        Button saveButton = (Button) metaDataPopUpMenu.findViewById(R.id.metaDataSave_button);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText authorTextBox = (EditText) metaDataPopUpMenu.findViewById(R.id.author_textBox);
                EditText emailTextBox = (EditText) metaDataPopUpMenu.findViewById(R.id.email_textBox);
                EditText descriptionTextBox = (EditText) metaDataPopUpMenu.findViewById(R.id.description_textBox);
                currentMetaData = new MetaData(authorTextBox.getText().toString(),emailTextBox.getText().toString(),descriptionTextBox.getText().toString());
                dialogWindow.dismiss();
            }
        });
        dialogWindow.setTitle("MetaData Information");
        dialogWindow.setContentView(metaDataPopUpMenu);
        dialogWindow.show();
    }

    public void inflateSaveProgramPopUp(){
        final View savePopUpMenu = getLayoutInflater().inflate(R.layout.save_popup,null);
        final Dialog dialogWindow = new Dialog(this);
        this.currentRules = addPredicates();

        Button saveButton = (Button) savePopUpMenu.findViewById(R.id.saveFile_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView aText = (TextView) savePopUpMenu.findViewById(R.id.save_textBox);
                TmpData tmp = new TmpData(MainActivity.this.currentRules, editorBox2.getText().toString(), console.getText().toString(), MainActivity.this.currentMetaData, MainActivity.this.declaredVariables);
                SaveFile save = new SaveFile(tmp);
                Boolean saved = save.saveProgram(aText.getText().toString(), MainActivity.this);
                if (saved){
                    console.append("\n-----------------------------");
                    console.append("\nSave Successful");
                    console.append("\n-----------------------------\n");
                }
                else{
                    console.append("\n-----------------------------");
                    console.append("\nSave Failed");
                    console.append("\n-----------------------------\n");
                }
                dialogWindow.dismiss();
            }
        });

        Button cancelButton = (Button) savePopUpMenu.findViewById(R.id.saveCancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialogWindow.dismiss();
            }
        });
        dialogWindow.setTitle("Save File");
        dialogWindow.setContentView(savePopUpMenu);
        dialogWindow.show();
    }

    public void inflateSavePrologPopUp(){
        final View savePopUpMenu = getLayoutInflater().inflate(R.layout.save_popup,null);
        final Dialog dialogWindow = new Dialog(this);
        this.currentRules = addPredicates();

        Button saveButton = (Button) savePopUpMenu.findViewById(R.id.saveFile_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView aText = (TextView) savePopUpMenu.findViewById(R.id.save_textBox);
                TmpData tmp = new TmpData(MainActivity.this.currentRules, editorBox2.getText().toString(), console.getText().toString(), MainActivity.this.currentMetaData, MainActivity.this.declaredVariables);
                SaveFile save = new SaveFile(tmp);
                Boolean saved = save.saveProlog(aText.getText().toString(), MainActivity.this);
                if (saved){
                    console.append("\n-----------------------------");
                    console.append("\nSave Successful");
                    console.append("\n-----------------------------\n");
                }
                else{
                    console.append("\n-----------------------------");
                    console.append("\nSave Failed");
                    console.append("\n-----------------------------\n");
                }
                dialogWindow.dismiss();
            }
        });

        Button cancelButton = (Button) savePopUpMenu.findViewById(R.id.saveCancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialogWindow.dismiss();
            }
        });
        dialogWindow.setTitle("Save Prolog File");
        dialogWindow.setContentView(savePopUpMenu);
        dialogWindow.show();
    }

    public void inflateOpenPrologPopUp(){
        final View openPopUpMenu = getLayoutInflater().inflate(R.layout.open_popup,null);
        final Dialog dialogWindow = new Dialog(this);

        Button loadButton = (Button) openPopUpMenu.findViewById(R.id.loadFile_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView aText = (TextView) openPopUpMenu.findViewById(R.id.load_textBox);
                LoadFile load = new LoadFile();
                Rules newRules = load.prolog(aText.getText().toString(), MainActivity.this);
                if (newRules != null){
                    loadRuleBlocks(newRules);
                    console.setText("");
                    console.append("-----------------------------");
                    console.append("\nLoad Successful");
                    console.append("\n-----------------------------\n");
                }
                else{
                    console.append("\n-----------------------------");
                    console.append("\nLoad Failed");
                    console.append("\n-----------------------------\n");
                }
                dialogWindow.dismiss();
            }
        });

        Button cancelButton = (Button) openPopUpMenu.findViewById(R.id.loadCancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialogWindow.dismiss();
            }
        });
        dialogWindow.setTitle("Load File");
        dialogWindow.setContentView(openPopUpMenu);
        dialogWindow.show();
    }

    public void inflateOpenProgramPopUp(){
        final View openPopUpMenu = getLayoutInflater().inflate(R.layout.open_popup,null);
        final Dialog dialogWindow = new Dialog(this);

        Button loadButton = (Button) openPopUpMenu.findViewById(R.id.loadFile_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView aText = (TextView) openPopUpMenu.findViewById(R.id.load_textBox);
                LoadFile load = new LoadFile();
                TmpData newData = load.program(aText.getText().toString(), MainActivity.this);
                if (newData != null){
                    loadRuleBlocks(newData.getRules());
                    MainActivity.this.currentMetaData = newData.getMetaData();
                    MainActivity.this.declaredVariables = newData.getDeclaredVariables();
                    editorBox2.setText(newData.getQuery());
                    console.setText("");
                    console.append("-----------------------------");
                    console.append("\nLoad Successful");
                    console.append("\n-----------------------------\n");
                    console.append(newData.getConsole());
                }
                else{
                    console.append("\n-----------------------------");
                    console.append("\nLoad Failed");
                    console.append("\n-----------------------------\n");
                }
                dialogWindow.dismiss();
            }
        });

        Button cancelButton = (Button) openPopUpMenu.findViewById(R.id.loadCancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dialogWindow.dismiss();
            }
        });
        dialogWindow.setTitle("Load File");
        dialogWindow.setContentView(openPopUpMenu);
        dialogWindow.show();
    }

    public void loadRuleBlocks(Rules tmpData){
        editorBox1.removeAllViews();
        HashMap<String,Rule> newRules = tmpData.getHash();
        Iterator it = newRules.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry pair = (Map.Entry) it.next();
            String predicateValue = pair.getKey().toString();
            if (pair.getValue() instanceof  RuleFacts){
                for (List<String> objectValue: ((RuleFacts) pair.getValue()).getValuePair()) {
                    if (objectValue.size() == 1){
                        final DisplaySingleConstant singleConstBlock = new DisplaySingleConstant(MainActivity.this);
                        singleConstBlock.setOnLongClickListener(new selectLongClick());
                        EditText predicateName = (EditText) singleConstBlock.findViewById(R.id.pred1);
                        predicateName.setText(predicateValue, TextView.BufferType.EDITABLE);
                        EditText constant = (EditText) singleConstBlock.findViewById(R.id.editConst1);
                        constant.setText(objectValue.get(0), TextView.BufferType.EDITABLE);
                        editorBox1.addView(singleConstBlock);
                    }
                    else if (objectValue.size() == 2){
                        final DisplayDoubleConstant doubleConstBlock = new DisplayDoubleConstant(MainActivity.this);
                        doubleConstBlock.setOnLongClickListener(new selectLongClick());
                        EditText predicateName = (EditText) doubleConstBlock.findViewById(R.id.pred2);
                        predicateName.setText(predicateValue, TextView.BufferType.EDITABLE);
                        EditText constant = (EditText) doubleConstBlock.findViewById(R.id.editConst2);
                        constant.setText(objectValue.get(0), TextView.BufferType.EDITABLE);
                        EditText constant2 = (EditText) doubleConstBlock.findViewById(R.id.editConst3);
                        constant2.setText(objectValue.get(1), TextView.BufferType.EDITABLE);
                        editorBox1.addView(doubleConstBlock);
                    }
                }
            }
            else if (pair.getValue() instanceof  MakeFacts){
                System.out.println("lol");
                final StartClass startBlock = new StartClass(MainActivity.this);
                EditText predicateName = (EditText) startBlock.findViewById(R.id.start_edittext);
                predicateName.setText(predicateValue);
                editorBox1.addView(startBlock);
                for (String rule:((MakeFacts) pair.getValue()).getValue()){
                    if (rule.contains("write")){
                        final WriteClass writeBlock = new WriteClass(MainActivity.this);
                        EditText contentEdit = (EditText) writeBlock.findViewById(R.id.editWrite);
                        String content = rule.replace("write('", "");
                        content = content.replace("')", "");
                        contentEdit.setText(content);
                        editorBox1.addView(writeBlock);
                    }
                    else if (rule.contains("read")){
                        final ReadClass readBlock = new ReadClass(MainActivity.this);
                        EditText nameEdit = (EditText) readBlock.findViewById(R.id.editRead1);
                        EditText valueEdit = (EditText) readBlock.findViewById(R.id.editRead2);
                        String content = rule.replace("read(", "");
                        content = content.replace(")", "");
                        nameEdit.setText(content);
                        System.out.println(content);
                        if (this.declaredVariables.getValue(content) != null){
                            valueEdit.setText(String.valueOf(this.declaredVariables.getValue(content)));
                        }
                        editorBox1.addView(readBlock);
                    }
                    else if (rule.contains("+")){
                        final Add_CustView addBlock =  new Add_CustView(MainActivity.this);
                        EditText rightSide = (EditText) addBlock.findViewById(R.id.empty2);
                        EditText leftSide = (EditText) addBlock.findViewById(R.id.empty1);
                        String[] expression = rule.split(" ");
                        rightSide.setText(expression[1]);
                        leftSide.setText(expression[0]);
                        editorBox1.addView(addBlock);
                    }
                    else if (rule.contains("-")){
                        final Minus_CustView minusBlock =  new Minus_CustView(MainActivity.this);
                        EditText rightSide = (EditText) minusBlock.findViewById(R.id.empty2);
                        EditText leftSide = (EditText) minusBlock.findViewById(R.id.empty1);
                        String[] expression = rule.split(" ");
                        rightSide.setText(expression[1]);
                        leftSide.setText(expression[0]);
                        editorBox1.addView(minusBlock);
                    }
                    else if (rule.contains("*")){
                        final Multiply_CustView multiBlock =  new Multiply_CustView(MainActivity.this);
                        EditText rightSide = (EditText) multiBlock.findViewById(R.id.empty2);
                        EditText leftSide = (EditText) multiBlock.findViewById(R.id.empty1);
                        String[] expression = rule.split(" ");
                        rightSide.setText(expression[1]);
                        leftSide.setText(expression[0]);
                        editorBox1.addView(multiBlock);
                    }
                    else if (rule.contains("/")){
                        final Divide_CustView divblock =  new Divide_CustView(MainActivity.this);
                        EditText rightSide = (EditText) divblock.findViewById(R.id.empty2);
                        EditText leftSide = (EditText) divblock.findViewById(R.id.empty1);
                        String[] expression = rule.split(" ");
                        rightSide.setText(expression[1]);
                        leftSide.setText(expression[0]);
                        editorBox1.addView(divblock);
                    }
                    else if (rule.contains(">")){
                        final MoreThan_CustView addBlock =  new MoreThan_CustView(MainActivity.this);
                        EditText rightSide = (EditText) addBlock.findViewById(R.id.empty2);
                        EditText leftSide = (EditText) addBlock.findViewById(R.id.empty1);
                        String[] expression = rule.split(" ");
                        rightSide.setText(expression[1]);
                        leftSide.setText(expression[0]);
                        editorBox1.addView(addBlock);
                    }
                    else if (rule.contains("<")){
                        final LessThan_CustView addBlock =  new LessThan_CustView(MainActivity.this);
                        EditText rightSide = (EditText) addBlock.findViewById(R.id.empty2);
                        EditText leftSide = (EditText) addBlock.findViewById(R.id.empty1);
                        String[] expression = rule.split(" ");
                        rightSide.setText(expression[1]);
                        leftSide.setText(expression[0]);
                        editorBox1.addView(addBlock);
                    }
                    else if (rule.contains(">=")){
                        final MoreEq_CustView addBlock =  new MoreEq_CustView(MainActivity.this);
                        EditText rightSide = (EditText) addBlock.findViewById(R.id.empty2);
                        EditText leftSide = (EditText) addBlock.findViewById(R.id.empty1);
                        String[] expression = rule.split(" ");
                        rightSide.setText(expression[1]);
                        leftSide.setText(expression[0]);
                        editorBox1.addView(addBlock);
                    }
                    else if (rule.contains("<=")){
                        final LessEq_CustView addBlock =  new LessEq_CustView(MainActivity.this);
                        EditText rightSide = (EditText) addBlock.findViewById(R.id.empty2);
                        EditText leftSide = (EditText) addBlock.findViewById(R.id.empty1);
                        String[] expression = rule.split(" ");
                        rightSide.setText(expression[1]);
                        leftSide.setText(expression[0]);
                        editorBox1.addView(addBlock);
                    }
                    else if (rule.contains("==")){
                        final Equal_CustView addBlock =  new Equal_CustView(MainActivity.this);
                        EditText rightSide = (EditText) addBlock.findViewById(R.id.empty2);
                        EditText leftSide = (EditText) addBlock.findViewById(R.id.empty1);
                        String[] expression = rule.split(" ");
                        rightSide.setText(expression[1]);
                        leftSide.setText(expression[0]);
                        editorBox1.addView(addBlock);
                    }
                }
            }
            it.remove();
        }
    }

    public void checkQueryLine(){
        String editorBox1Text = editorBox2.getText().toString();
        String[] lines = editorBox1Text.split("\n");
        LittleProlog littleProlog = new LittleProlog(this.currentRules);
        console = (TextView) console.findViewById(R.id.console_text);
        count = 0;
        for (String line:lines) {
            line = line.replace("?- ","");
            System.out.println(line);
            if (line.endsWith(".")){
                ReadQuery readQuery = new ReadQuery(line);
                predicate = readQuery.getPredicate();
                if (line.contains("(") && line.contains(")")){
                    console.append(littleProlog.runQuery(line,"",0));
                }
                else{
                    Boolean result = false;
                    Boolean rejected = false;
                    System.out.println(this.currentRules.getHash().containsKey(predicate));
                    if (this.currentRules.getHash().containsKey(predicate)){
                        List<String> rulesObject = this.currentRules.getHash().get(predicate).getValue();
                        for (String rule:rulesObject){
                            if (rule.contains("write")){
                                String content = rule.replace("write('", "");
                                content = content.replace("')", "");
                                console.append("\n" + content);
                            }
                            else{
                                if (!rule.contains("read")){
                                    String[] expression = rule.split(" ");
                                    System.out.println(rule);
                                    Integer opCount = 0;
                                    for (String exp:expression) {
                                        if (this.currentRules.containsOperator(exp)){
                                            opCount += 1;
                                        }
                                    }
                                    if (opCount == 1) {
                                        Expression exp = new Expression(this.declaredVariables.getValue(expression[0]), expression[1], this.declaredVariables.getValue(expression[2]));
                                        result = exp.getResult();
                                    } else if (opCount == 2) {
                                        Expression subExp = new Expression(this.declaredVariables.getValue(Character.toString(expression[2].charAt(0))), Character.toString(expression[2].charAt(1)), this.declaredVariables.getValue(Character.toString(expression[2].charAt(2))));
                                        Expression exp = new Expression(this.declaredVariables.getValue(expression[0]), expression[1], subExp);
                                        result = exp.getResult();
                                    }
                                    if (!result) {
                                        rejected = true;
                                    }
                                }
                            }
                        }
                    }
                    if (!rejected){
                        console.append("\n" + "Yes");
                    }
                    else{
                        console.append("\n" + "No");
                    }
                }
            }
            else if (line.equals(";")){
                count += 1;
                System.out.println(predicate + count);
                console.append(littleProlog.runQuery(line,predicate,count));
            }
        }
    }

    public Rules addPredicates(){
        this.currentRules.clearHash();
        WriteRules write = new WriteRules(this.currentRules);
        this.currentRules.clearHash();
        Boolean makeRule = false;
        String predicateValue = "";
        try{
            for (int x = 0; x < editorBox1.getChildCount(); x++){
                View theBlock = (View) editorBox1.getChildAt(x);
                if (theBlock instanceof DisplaySingleConstant) {
                    if (makeRule){
                        throw new Exception();
                    }
                    DisplaySingleConstant currentBlock = (DisplaySingleConstant) theBlock;
                    EditText predicateName = (EditText) currentBlock.findViewById(R.id.pred1);
                    EditText factArgument = (EditText) currentBlock.findViewById(R.id.editConst1);
                    List<String> arguments = new ArrayList<>();
                    arguments.add(factArgument.getText().toString());
                    write.addPredicateVersion1(predicateName.getText().toString(),arguments);
                }
                else if (theBlock instanceof DisplayDoubleConstant){
                    if (makeRule){
                        throw new Exception();
                    }
                    DisplayDoubleConstant currentBlock = (DisplayDoubleConstant) theBlock;
                    EditText predicateName = (EditText) currentBlock.findViewById(R.id.pred2);
                    EditText factArgument = (EditText) currentBlock.findViewById(R.id.editConst2);
                    EditText factArgument2 = (EditText) currentBlock.findViewById(R.id.editConst3);
                    List<String> arguments = new ArrayList<>();
                    arguments.add(factArgument.getText().toString());
                    arguments.add(factArgument2.getText().toString());
                    write.addPredicateVersion1(predicateName.getText().toString(),arguments);
                }
                else if (theBlock instanceof  StartClass){
                    StartClass currentBlock = (StartClass) theBlock;
                    EditText predicateName = (EditText) currentBlock.findViewById(R.id.start_edittext);
                    makeRule = true;
                    predicateValue = predicateName.getText().toString();
                    write.addPredicateVersion2(predicateValue,null);
                }
                else if (theBlock instanceof WriteClass){
                    if (!makeRule){
                        throw new Exception();
                    }
                    WriteClass currentBlock = (WriteClass) theBlock;
                    EditText writeContent = (EditText) currentBlock.findViewById(R.id.editWrite);
                    String expression = "write('" + writeContent.getText().toString() + "')";
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof ReadClass){
                    if (!makeRule){
                        throw new Exception();
                    }
                    ReadClass currentBlock = (ReadClass) theBlock;
                    EditText variableName = (EditText) currentBlock.findViewById(R.id.editRead1);
                    EditText variableValue = (EditText) currentBlock.findViewById(R.id.editRead2);
                    this.declaredVariables.declareVariable(variableName.getText().toString());
                    try {
                        this.declaredVariables.assgnVariable(variableName.getText().toString(),Integer.parseInt(variableValue.getText().toString()));
                    }
                    catch (Exception e){
                        return null;
                    }
                    String expression = "read(" + variableName.getText().toString() + ")";
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof  Add_CustView){
                    if (!makeRule){
                        throw new Exception();
                    }
                    Add_CustView currentBlock = (Add_CustView) theBlock;
                    EditText leftSide = (EditText) currentBlock.findViewById(R.id.empty1);
                    EditText rightSide = (EditText) currentBlock.findViewById(R.id.empty2);
                    String expression = leftSide.getText().toString() + " + " + rightSide.getText().toString();
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof Divide_CustView){
                    if (!makeRule){
                        throw new Exception();
                    }
                    Divide_CustView currentBlock = (Divide_CustView) theBlock;
                    EditText leftSide = (EditText) currentBlock.findViewById(R.id.empty1);
                    EditText rightSide = (EditText) currentBlock.findViewById(R.id.empty2);
                    String expression = leftSide.getText().toString() + " / " + rightSide.getText().toString();
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof Equal_CustView){
                    if (!makeRule){
                        throw new Exception();
                    }
                    Equal_CustView currentBlock = (Equal_CustView) theBlock;
                    EditText leftSide = (EditText) currentBlock.findViewById(R.id.empty1);
                    EditText rightSide = (EditText) currentBlock.findViewById(R.id.empty2);
                    String expression = leftSide.getText().toString() + " == " + rightSide.getText().toString();
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof LessEq_CustView){
                    if (!makeRule){
                        throw new Exception();
                    }
                    LessEq_CustView currentBlock = (LessEq_CustView) theBlock;
                    EditText leftSide = (EditText) currentBlock.findViewById(R.id.empty1);
                    EditText rightSide = (EditText) currentBlock.findViewById(R.id.empty2);
                    String expression = leftSide.getText().toString() + " <= " + rightSide.getText().toString();
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof LessThan_CustView){
                    if (!makeRule){
                        throw new Exception();
                    }
                    LessThan_CustView currentBlock = (LessThan_CustView) theBlock;
                    EditText leftSide = (EditText) currentBlock.findViewById(R.id.empty1);
                    EditText rightSide = (EditText) currentBlock.findViewById(R.id.empty2);
                    String expression = leftSide.getText().toString() + " < " + rightSide.getText().toString();
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof Minus_CustView){
                    if (!makeRule){
                        throw new Exception();
                    }
                    Minus_CustView currentBlock = (Minus_CustView) theBlock;
                    EditText leftSide = (EditText) currentBlock.findViewById(R.id.empty1);
                    EditText rightSide = (EditText) currentBlock.findViewById(R.id.empty2);
                    String expression = leftSide.getText().toString() + " - " + rightSide.getText().toString();
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof MoreEq_CustView){
                    if (!makeRule){
                        throw new Exception();
                    }
                    MoreEq_CustView currentBlock = (MoreEq_CustView) theBlock;
                    EditText leftSide = (EditText) currentBlock.findViewById(R.id.empty1);
                    EditText rightSide = (EditText) currentBlock.findViewById(R.id.empty2);
                    String expression = leftSide.getText().toString() + " >= " + rightSide.getText().toString();
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof MoreThan_CustView){
                    if (!makeRule){
                        throw new Exception();
                    }
                    MoreThan_CustView currentBlock = (MoreThan_CustView) theBlock;
                    EditText leftSide = (EditText) currentBlock.findViewById(R.id.empty1);
                    EditText rightSide = (EditText) currentBlock.findViewById(R.id.empty2);
                    String expression = leftSide.getText().toString() + " > " + rightSide.getText().toString();
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof Multiply_CustView){
                    if (!makeRule){
                        throw new Exception();
                    }
                    Multiply_CustView currentBlock = (Multiply_CustView) theBlock;
                    EditText leftSide = (EditText) currentBlock.findViewById(R.id.empty1);
                    EditText rightSide = (EditText) currentBlock.findViewById(R.id.empty2);
                    String expression = leftSide.getText().toString() + " * " + rightSide.getText().toString();
                    write.addPredicateVersion2(predicateValue,expression);
                }
                else if (theBlock instanceof EndClass){
                    makeRule = false;
                }
            }
            return write.getRules();
        }
        catch(Exception e){
            return null;
        }

    }
}


