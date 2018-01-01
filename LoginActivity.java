package com.example.danny.afinal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends Activity {

    TextView textView;
    Button getMessgeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView = (TextView) findViewById(R.id.editText);
        //messageTextView= (TextView) findViewById(R.id.);

        SharedPreferences pref = getSharedPreferences("pref", LoginActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("intent_pref", "email@domain.com");
        editor.commit();

        final String defaultEmail = pref.getString("default", "email@domain");

        textView.setText(defaultEmail);
        if (getSharedPreferences("pref", 0) != null) {
            //String savedMessage = pref.getString("intent_pref", "");
            textView.setText(pref.getString("pref", ""));
        }


            getMessgeButton = (Button) findViewById(R.id.loginButton);

            getMessgeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {



                            SharedPreferences pref = getSharedPreferences("pref", LoginActivity.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("pref", textView.getText().toString());
                            //String pref2=pref.getString("pref","");
                            //textView.setText(pref2);
                            editor.commit();


                            Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                            startActivity(intent);


                        }
                        });


        }
    //}
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        //if (requestCode == 99) {
          //  if (resultCode == Activity.RESULT_OK) {
            //    String message = data.getStringExtra("MESSAGE_DATA");
              //  messageTextView.setText(message);
            //}
        //}


    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e("LoginActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("LoginActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("LoginActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("LoginActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("LoginActivity", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("LoginActivity", "onRestart");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("LoginActivity", "onSaveInstanceState");
        if(isChangingConfigurations())
            Log.e("LoginActivity", "is ChangingConfigurations");
        else
            Log.e("LoginActivity", "is not ChangingConfigurations");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("LoginActivity", "onRestoreInstanceState");
    }
}
