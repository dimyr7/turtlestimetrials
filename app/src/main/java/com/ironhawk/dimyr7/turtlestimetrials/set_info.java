package com.ironhawk.dimyr7.turtlestimetrials;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class set_info extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_info);
        Button next_button = (Button)findViewById(R.id.next_button2);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView s1 = (TextView)findViewById(R.id.set_info1);
                TextView s2 = (TextView)findViewById(R.id.set_info2);
                EditText swimsText = (EditText)findViewById(R.id.swims);
                String swimsStr = swimsText.getText().toString();
                if(swimsStr.equals("") || swimsStr.equals("-")){
                    s1.setText("Please enter a valid positive number");
                    swimsText.setText("");
                    return;
                }
                int numSwims = Integer.parseInt(swimsStr);
                if(numSwims<=0){

                    s1.setText("Please enter a valid positive number");
                    swimsText.setText("");
                    return;
                }

                EditText pauseText = (EditText)findViewById(R.id.pause);
                String pauseStr = pauseText.getText().toString();
                if(pauseStr.equals("") || pauseStr.equals("-")){
                    s2.setText("Please enter a valid positive number");
                    pauseText.setText("");
                    return;
                }
                long numPause = Long.parseLong(pauseStr)*1000;
                if(numPause<=0){

                    s2.setText("Please enter a valid positive number");
                    pauseText.setText("");
                    return;
                }


                SwimSet.setRepeatTimes(numSwims);
                SwimSet.setPauseTime(numPause);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(pauseText.getWindowToken(), 0);

                Intent myIntent = new Intent(v.getContext(), intervals.class);
                startActivity(myIntent);
                finish();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_info, menu);
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
