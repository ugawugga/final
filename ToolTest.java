package com.example.danny.afinal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Field;

public class ToolTest extends AppCompatActivity {
    EditText change;

    static String custom=new String("this is a creative string to satisfy a requirement for lab 8");
    static String more=new String("This is the string that will be changed");
    private void makeActionOverflowMenuShown() {
        //devices with hardware menu button (e.g. Samsung Note) don't show action overflow menu
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");

                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);

        } catch (Exception e) {
            Log.d(null, e.getLocalizedMessage());
        }
    }
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_test);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, custom, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //makeActionOverflowMenuShown();



    }
    public boolean onOptionsItemSelected(MenuItem mi){

        if (mi.getItemId()==R.id.action_one) {
            Log.d("Toolbar", "Option 1 selected");
            Snackbar.make(this.findViewById(android.R.id.content), more, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (mi.getItemId()==R.id.action_two){
            Log.d("Toolbar", "Option 2 selected");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Do you want to go back?");
// Add the buttons
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                    // User clicked OK button
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
// Create the AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();


        } else if (mi.getItemId()==R.id.action_three){
            Log.d("Toolbar", "Option 3 selected");
            LayoutInflater inf = LayoutInflater.from(this);
            final View inflater = inf.inflate(R.layout.dialog, null);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            alert.setTitle("Would you like to change the message?");
            alert.setView(inflater);

            final EditText change=(EditText)inflater.findViewById(R.id.username);


            alert.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton)
                {
                    more=change.getText().toString();

                    //System.out.println(s1);
                    //do operations using s1 and s2 here...
                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.cancel();
                }
            });

            alert.show();
            // Code from https://stackoverflow.com/questions/6626006/android-custom-dialog-cant-get-text-from-edittext

        } else if (mi.getItemId()==R.id.info){
            Log.d("Toolbar", "Option 3 selected");
            Toast.makeText(getApplicationContext(), "By Danny Reilander", Toast.LENGTH_LONG).show();


        }
        //Start an activity…
        return true;


    }

}
