package com.ironhawk.dimyr7.turtlestimetrials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class logging extends Activity {
    LinearLayout rel;

    long[][] startTimes = new long[3][5];
    long[][] shouldStartTimes = new long[3][5];
    long[][] endTimes = new long[3][5];
    long[][] shouldEndTimes = new long[3][5];

    int[] numSwimmersPerLane = new int[3];
    int[][] swimsMade = new int[3][5];
    int[] leadInLane = new int[3];

    Button[] lanes = new Button[3];

    boolean[] flags = new boolean[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logging);
        rel = (LinearLayout)findViewById(R.id.logging_relative);
        lanes[2] = (Button)findViewById(R.id.logging_lane3);
        lanes[1] = (Button)findViewById(R.id.logging_lane2);
        lanes[0] = (Button)findViewById(R.id.logging_lane1);

        for(int i = 0; i <3; i++){
            numSwimmersPerLane[i] = SwimSet.getNumSwimmersInLane(i);
            leadInLane[i] = 0;
            flags[i] = false;
            for(int j = 0; j < numSwimmersPerLane[i]; j++){
                swimsMade[i][j]=0;
                startTimes[i][j] = System.currentTimeMillis()+SwimSet.getPauseTime()*j;
                shouldStartTimes[i][j] = System.currentTimeMillis()+SwimSet.getPauseTime()*j;
                shouldEndTimes[i][j] = shouldStartTimes[i][j]+SwimSet.getInterval(swimsMade[i][j]);
            }
        }
        lanes[2].setOnClickListener(onClickLog(2));
        lanes[1].setOnClickListener(onClickLog(1));
        lanes[0].setOnClickListener(onClickLog(0));
    }

    public View.OnClickListener onClickLog(final int lane){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v){
                endTimes[lane][leadInLane[lane]] = System.currentTimeMillis();
                int lead = leadInLane[lane];

                long startTime = startTimes[lane][lead];
                long endTime = endTimes[lane][lead];
                long shouldStartTime = shouldStartTimes[lane][lead];
                long shouldEndTime = shouldEndTimes[lane][lead];

                long timeTaken = endTime-startTime;
                SwimSet.newLogEntry(lane, lead, timeTaken);
                if(endTime > shouldEndTime){
                    startTimes[lane][lead] = endTime;
                }
                else{
                    startTimes[lane][lead] = shouldEndTime;
                }
                endTimes[lane][lead] = 0;
                shouldStartTimes[lane][lead] = shouldEndTime;
                shouldEndTimes[lane][lead] = shouldEndTime+SwimSet.getInterval(swimsMade[lane][lead]);
                swimsMade[lane][lead]++;
                leadInLane[lane]=(lead+1)%SwimSet.getNumSwimmersInLane(lane);
                if(lead+1==SwimSet.getNumSwimmersInLane(lane) && swimsMade[lane][lead] == SwimSet.getRepeatTimes()){
                    flags[lane] = true;
                    lanes[lane].setText("");
                    lanes[lane].setBackgroundColor(256*256*256-1);

                }
                if(flags[0] && flags[1] && flags[2]){
                    /*
                    TODO go to data sheet view
                     */
                    setContentView(R.layout.activity_welcome_page);
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logging, menu);
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
