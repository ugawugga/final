package com.example.danny.afinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends Activity {
    TextView messageTextView;
    Button getButton;
    Button getCButton;
    Button getWButton;
    Button getTButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.e("StartActivity", "onCreate");

        getButton = (Button) findViewById(R.id.button);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, ListItemsActivity.class);
                startActivityForResult(intent, 10);}


            });
        getCButton = (Button) findViewById(R.id.chatButton);
        getCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, ChatWindow.class);
                startActivity(intent);
                Log.i("StartActivity","User clicked Start Chat");}


        });
        getWButton = (Button) findViewById(R.id.weatherButton);
        getWButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, WeatherForecast.class);
                startActivity(intent);
                Log.i("WeatherActivity","User clicked Weather Forecast");}


    });
        getTButton = (Button) findViewById(R.id.tool);
        getTButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, ToolTest.class);
                startActivity(intent);
                Log.i("StartActivity","User clicked Test tools");}


        });





};




    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 10) {
            Log.i("StartActivity", "Returned to StartActivity.onActivityResult");

        }
        if (resultCode == Activity.RESULT_OK) {
        //String message = data.getStringExtra("MESSAGE_DATA");
        //messageTextView.setText(message);
            String messagePassed = data.getStringExtra("Response");
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(this, messagePassed, duration);
            toast.show();
        }



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("StartActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("StartActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("StartActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("StartActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("StartActivity", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("StartActivity", "onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("StartActivity", "onSaveInstanceState");
        if(isChangingConfigurations())
            Log.e("StartActivity", "is ChangingConfigurations");
        else
            Log.e("StartActivity", "is not ChangingConfigurations");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("StartActivity", "onRestoreInstanceState");
    }
}
