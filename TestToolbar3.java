package com.example.danny.afinal;

import android.app.Activity;
import android.os.Bundle;

public class TestToolbar3 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar3);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
