package com.example.dageer2_simpale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Test mTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();

        DaggerMainConponent.builder().build().inject(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mTest == null)
            Log.e("TAG", "Test 为 null");
        else
            Log.e("TAG", "Test 不为 null");
    }
}
