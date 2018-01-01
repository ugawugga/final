package com.example.danny.afinal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class TestToolbar extends AppCompatActivity {

    Toolbar testTool;
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem mi){
        if (mi.getItemId()==R.id.action_one) {
            Log.d("Toolbar", "Option 1 selected");
        } else if (mi.getItemId()==R.id.action_two){
            Log.d("Toolbar", "Option 2 selected");

        } else if (mi.getItemId()==R.id.action_three){
            Log.d("Toolbar", "Option 3 selected");

        }
        //Start an activity…
        return true;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar myToolbar=(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


    }



}
