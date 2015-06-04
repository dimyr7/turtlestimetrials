package com.ironhawk.dimyr7.turtlestimetrials;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class enter_swimmers extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_swimmers);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        final Button next = (Button)findViewById(R.id.next_button1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText[][] swimmers = new EditText[3][5];
                swimmers[0][0] = (EditText)findViewById(R.id.lane1_1);
                swimmers[0][1] = (EditText)findViewById(R.id.lane1_2);
                swimmers[0][2] = (EditText)findViewById(R.id.lane1_3);
                swimmers[0][3] = (EditText)findViewById(R.id.lane1_4);
                swimmers[0][4] = (EditText)findViewById(R.id.lane1_5);
                swimmers[1][0] = (EditText)findViewById(R.id.lane2_1);
                swimmers[1][1] = (EditText)findViewById(R.id.lane2_2);
                swimmers[1][2] = (EditText)findViewById(R.id.lane2_3);
                swimmers[1][3] = (EditText)findViewById(R.id.lane2_4);
                swimmers[1][4] = (EditText)findViewById(R.id.lane2_5);
                swimmers[2][0] = (EditText)findViewById(R.id.lane3_1);
                swimmers[2][1] = (EditText)findViewById(R.id.lane3_2);
                swimmers[2][2] = (EditText)findViewById(R.id.lane3_3);
                swimmers[2][3] = (EditText)findViewById(R.id.lane3_4);
                swimmers[2][4] = (EditText)findViewById(R.id.lane3_5);
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 5; j++){
                        String name = swimmers[i][j].getText().toString();
                        if(!name.equals("")){
                            SwimSet.setSwimmer(i, j, name);
                            SwimSet.setNumSwimmersInLane(i, j+1);
                        }
                    }
                }

                Intent myIntent = new Intent(v.getContext(), set_info.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_enter_swimmers, menu);
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
