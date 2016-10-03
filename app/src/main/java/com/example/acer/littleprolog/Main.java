package com.example.acer.littleprolog;

import com.example.acer.littleprolog.R;   //IMPORTANT, or else will get "package R does not exist"

import android.content.ClipData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.view.MenuItem;
import android.widget.ScrollView;

public class Main extends AppCompatActivity {

    private ScrollView consoleBox;
    private LinearLayout blockContainer,editorBox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editorBox1 = (LinearLayout) findViewById(R.id.editor1_linlay);
        consoleBox = (ScrollView)findViewById(R.id.console);
        blockContainer = (LinearLayout) findViewById(R.id.block_container_linlay);

        //set drag items listener
        View more_than = findViewById(R.id.more_than_box);
        more_than.setOnTouchListener(new draggableObjectListener());

        //set drop items listener
        editorBox1.setOnDragListener(new dropDestinationObjectListener());

        //on click listener for clear button
        findViewById(R.id.clear_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.getStarted:
                                if((editorBox1).getChildCount() > 0)
                                (editorBox1).removeAllViews();
                                return true;

                            case R.id.tutorial:
                                if((consoleBox).getChildCount() > 0)
                                (consoleBox).removeAllViews();
                                return true;

                            case R.id.checkUpdates:

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

    }

    //Create touch listener
    private final class draggableObjectListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);       //start dragging the items
                Log.d("touch", "here");
                return true;
            } else {
                return false;
            }
        }
    }

    //Create drop listener
    class dropDestinationObjectListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View view, DragEvent dragEvent) {

            switch (dragEvent.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:


                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    break;

                default:
                    break;

            }
            return true;
        }
    }
}


