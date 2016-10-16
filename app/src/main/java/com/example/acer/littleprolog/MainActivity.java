/*  @AUTHOR: SEONG HAN & IVAN KOH
*   @DATE CREATED: 29 SEPT 2016
*   @DATE MODIFIED: 5 OCT 2016
*   @PURPOSE: This is the main activity class for the application
* */

package com.example.acer.littleprolog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //initialize global variable
    private LinearLayout blockContainer,editorBox1,consoleBox;
    private EditText editorBox2;
    private Button clear_btn,open_btn,run_btn,save_btn,metaData_btn,delete_btn,single_const_btn,
            double_const_btn,write_btn,read_btn,operator_btn,start_btn,end_btn;
    private View selectedView;
    private TextView console;
    private Rules currentRules = new Rules();
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
        operator_btn = (Button)findViewById(R.id.operator_button);
        start_btn = (Button)findViewById(R.id.start_button) ;
        end_btn = (Button)findViewById(R.id.end_button);

        //set the initial text to editorBox2
        editorBox2.setText("?- ");

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
                                currentRules = addPredicates();
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
                editorBox1.addView(startButton);       //add start block into Insert Rules and Facts column
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

        //listener for operator button, to show a operator... But this have not function yet, it
        // will be done in the next iteration.
        operator_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);   //get PopupMenu class
                //set listener for menu, so that when user click on the button, popup menu will be shown
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
                TmpData tmp = new TmpData(MainActivity.this.currentRules, editorBox2.getText().toString(), console.getText().toString(), currentMetaData);
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
                TmpData tmp = new TmpData(MainActivity.this.currentRules, editorBox2.getText().toString(), console.getText().toString(), currentMetaData);
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
                    currentMetaData = newData.getMetaData();
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
            String predicate = pair.getKey().toString();
            if (pair.getValue() instanceof  RuleFacts){
                for (List<String> objectValue: ((RuleFacts) pair.getValue()).getValuePair()) {
                    if (objectValue.size() == 1){
                        final DisplaySingleConstant singleConstBlock = new DisplaySingleConstant(MainActivity.this);
                        singleConstBlock.setOnLongClickListener(new selectLongClick());
                        EditText predicateName = (EditText) singleConstBlock.findViewById(R.id.pred1);
                        predicateName.setText(predicate, TextView.BufferType.EDITABLE);
                        EditText constant = (EditText) singleConstBlock.findViewById(R.id.editConst1);
                        constant.setText(objectValue.get(0), TextView.BufferType.EDITABLE);
                        editorBox1.addView(singleConstBlock);
                    }
                    else if (objectValue.size() == 2){
                        final DisplayDoubleConstant doubleConstBlock = new DisplayDoubleConstant(MainActivity.this);
                        doubleConstBlock.setOnLongClickListener(new selectLongClick());
                        EditText predicateName = (EditText) doubleConstBlock.findViewById(R.id.pred2);
                        predicateName.setText(predicate, TextView.BufferType.EDITABLE);
                        EditText constant = (EditText) doubleConstBlock.findViewById(R.id.editConst2);
                        constant.setText(objectValue.get(0), TextView.BufferType.EDITABLE);
                        EditText constant2 = (EditText) doubleConstBlock.findViewById(R.id.editConst3);
                        constant2.setText(objectValue.get(1), TextView.BufferType.EDITABLE);
                        editorBox1.addView(doubleConstBlock);
                    }
                }
            }
            else if (pair.getValue() instanceof  MakeFacts){

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
                console.append(littleProlog.runQuery(line,"",0));
                Integer index = line.indexOf("(");
                predicate = line.substring(0,index);
            }
            else if (line.equals(";")){
                count += 1;
                System.out.println(predicate + count);
                console.append(littleProlog.runQuery(line,predicate,count));
            }
        }
    }

    public Rules addPredicates(){
        WriteRules write = new WriteRules(this.currentRules);
        this.currentRules.clearHash();
        for (int x = 0; x < editorBox1.getChildCount(); x++){
            View theBlock = (View) editorBox1.getChildAt(x);
            if (theBlock instanceof DisplaySingleConstant) {
                DisplaySingleConstant currentBlock = (DisplaySingleConstant) theBlock;
                EditText predicateName = (EditText) currentBlock.findViewById(R.id.pred1);
                EditText factArgument = (EditText) currentBlock.findViewById(R.id.editConst1);
                List<String> arguments = new ArrayList<>();
                arguments.add(factArgument.getText().toString());
                System.out.println(arguments.get(0));
                write.addPredicateVersion1(predicateName.getText().toString(),arguments);
            }
            else if (theBlock instanceof DisplayDoubleConstant){
                DisplayDoubleConstant currentBlock = (DisplayDoubleConstant) theBlock;
                EditText predicateName = (EditText) currentBlock.findViewById(R.id.pred2);
                EditText factArgument = (EditText) currentBlock.findViewById(R.id.editConst2);
                EditText factArgument2 = (EditText) currentBlock.findViewById(R.id.editConst3);
                List<String> arguments = new ArrayList<>();
                arguments.add(factArgument.getText().toString());
                arguments.add(factArgument2.getText().toString());
                write.addPredicateVersion1(predicateName.getText().toString(),arguments);
            }
        }
        return write.getRules();
    }
}


