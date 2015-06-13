package com.ironhawk.dimyr7.turtlestimetrials;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class spreadsheet extends Activity {
    double[][][] times;
    int[][] timesEntered = new int[3][5];
    int[] numOfSwimersPerLane = new int[3];
    TextView[][] nameTimes = new TextView[3][5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spreadsheet);
        int numOfSwims = SwimSet.getRepeatTimes();
        times = new double[3][5][numOfSwims];

        for(int i = 0; i < 3; i++){
            numOfSwimersPerLane[i] = SwimSet.getNumSwimmersInLane(i);
            for(int j = 0; j < numOfSwimersPerLane[i]-1; j++){
                timesEntered[i][j] = 0;
            }
        }
        ArrayList<Integer> laneLog = SwimSet.getLaneLog();
        ArrayList<Integer> heatLog = SwimSet.getHeatLog();
        ArrayList<Long> timeLog = SwimSet.getTimeLog();

        for(int i = 0; i < timeLog.size(); i++){
            long time = timeLog.get(i);
            int heat = heatLog.get(i);
            int lane = laneLog.get(i);

            times[lane][heat][timesEntered[lane][heat]] = time;
            timesEntered[lane][heat]++;
        }

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < numOfSwimersPerLane[i]; j++){
                nameTimes[i][j] = new TextView(findViewById(R.id.spreadsheetLinLayout2).getContext());
                nameTimes[i][j].setText(SwimSet.getSwimmer(i,j)+"\n");
                for(int k = 0; k < numOfSwims; k++){
                    nameTimes[i][j].append(times[i][j][k]/1000.+"\n");
                }
                ((LinearLayout)findViewById(R.id.spreadsheetLinLayout2)).addView(nameTimes[i][j]);
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spreadsheet, menu);
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
