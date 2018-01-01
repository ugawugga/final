package com.example.danny.afinal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;


public class ListItemsActivity extends Activity {
    ImageButton getCam;
    Switch getSwitch;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.e("ListActivity", "onCreate");

        final Context context=this;
        getCam = (ImageButton) findViewById(R.id.cam);
        getCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent takePictureIntent = new Intent(ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }


        });
        getSwitch = (Switch) findViewById(R.id.switch2);
        getSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    CharSequence text = "Switch is On";// "Switch is Off"
                    int duration = Toast.LENGTH_SHORT;
                    //= Toast.LENGTH_LONG if Off

                    Toast toast = Toast.makeText(ListItemsActivity.this, text, duration); //this is the ListActivity
                    toast.show(); //display your message box
                } else {
                    CharSequence text = "Switch is Off";// "Switch is Off"
                    int duration = Toast.LENGTH_LONG;
                    //= Toast.LENGTH_LONG if Off

                    Toast toast = Toast.makeText(ListItemsActivity.this, text, duration); //this is the ListActivity
                    toast.show(); //display your message box
                }
            }
        });

        CheckBox getCheckbox;
        getCheckbox = (CheckBox) findViewById(R.id.checkBox2);
        getCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
// 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

                        .setTitle(R.string.dialog_title)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User clicked OK button
                                Intent resultIntent = new Intent(  );
                                resultIntent.putExtra("Responsesz", "Here is my response");
                                setResult(Activity.RESULT_OK, resultIntent);
                                finish();

                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        })
                        .show();



            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
         Bundle extras = data.getExtras();
         Bitmap imageBitmap = (Bitmap) extras.get("data");
        getCam.setImageBitmap(imageBitmap);
     }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("ListActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ListActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("ListActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("ListActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("ListActivity", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("ListActivity", "onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("ListActivity", "onSaveInstanceState");
        if(isChangingConfigurations())
            Log.e("ListActivity", "is ChangingConfigurations");
        else
            Log.e("ListActivity", "is not ChangingConfigurations");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("ListActivity", "onRestoreInstanceState");
    }

}
