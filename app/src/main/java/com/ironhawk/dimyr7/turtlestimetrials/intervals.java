package com.ironhawk.dimyr7.turtlestimetrials;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class intervals extends Activity {
    ArrayList<EditText> intervals = new ArrayList<EditText>();
    private LinearLayout mLayout;
    private CheckBox check;
    private EditText example;
    private int numOfSwims;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervals);

        check = (CheckBox)findViewById(R.id.sameIntervalCheck);
        mLayout = (LinearLayout)findViewById(R.id.intervalLinLayout2);
        example = (EditText)findViewById(R.id.timeEx);
        numOfSwims = SwimSet.getRepeatTimes();
        start = (Button)findViewById(R.id.intervals_start);

        intervals.add(example);
        for (int i = 1; i < numOfSwims; i++) {
            EditText editToAdd = new EditText(check.getContext());
            editToAdd.setInputType(example.getInputType());
            mLayout.addView(editToAdd);
            intervals.add(editToAdd);
        }
        check.setOnClickListener(onClickCheck());
        start.setOnClickListener(onClickStart());

    }

    private View.OnClickListener onClickCheck() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check.isChecked()) {
                    for(int i = numOfSwims-1; i >= 1 ; i--){
                       // ((ViewManager)findViewById(R.id.intervals_linearLayout)).removeView(intervals.get(i));
                        mLayout.removeView(intervals.get(i));
                        intervals.remove(i);
                    }
                }
                else{
                    for (int i = 1; i < numOfSwims; i++) {
                        EditText editToAdd = new EditText(v.getContext());
                        editToAdd.setInputType(example.getInputType());
                        mLayout.addView(editToAdd);
                        intervals.add(editToAdd);
                    }
                }
            }
        };
    }

    public View.OnClickListener onClickStart(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(check.isChecked()){
                    EditText child = (EditText)findViewById(R.id.timeEx);
                    if(child.getText().toString().equals("")){
                        return;
                    }
                }
                else{
                    int childCount = mLayout.getChildCount();
                    for(int i = 0; i < childCount; i++){
                        View child = mLayout.getChildAt(i);
                        if(child instanceof EditText){
                            if(((EditText) child).getText().toString().equals("")){
                                return;
                            }
                        }
                    }
                }
                int childCount = mLayout.getChildCount();
                for(int i = 0; i < childCount; i++){
                    View child = mLayout.getChildAt(i);
                    if(child instanceof EditText){
                        String[] timeStr = ((EditText) child).getText().toString().split(":");
                        long time;
                        if(timeStr.length == 1){
                            time = Long.parseLong(timeStr[0])*1000;
                        }
                        else {
                            time = Long.parseLong(timeStr[0]) * 60000 + Long.parseLong(timeStr[1]) * 1000;
                        }
                        if(check.isChecked()){
                            int numSwims = SwimSet.getRepeatTimes();
                            for(int j = 0; j<numSwims;j++ ){
                                SwimSet.addInterval(time);
                            }
                        }
                        else {
                            SwimSet.addInterval(time);
                        }
                    }
                }
                Intent myIntent = new Intent(v.getContext(), logging.class);
                startActivity(myIntent);
                finish();


            }
        };
    }
    private static void makeFewIntervals(View v){

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intervals, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
