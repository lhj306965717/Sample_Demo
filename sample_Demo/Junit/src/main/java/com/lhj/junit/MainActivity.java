package com.lhj.junit;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public TextView mTv;
    public AppCompatEditText mEt;
    public Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
    }

    private void initEvent() {
        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random(SystemClock.currentThreadTimeMillis());

                long aLong = random.nextLong();

                Log.e("TAG", ""+aLong);

                mTv.setText(String.valueOf(aLong));
            }
        });
    }

    private void initView() {
        mTv = findViewById(R.id.tv);
        mEt = findViewById(R.id.et);
        mBt = findViewById(R.id.bt);
    }
}
