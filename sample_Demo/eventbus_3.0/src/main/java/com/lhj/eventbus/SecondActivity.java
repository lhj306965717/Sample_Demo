package com.lhj.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/9/5.
 */

public class SecondActivity extends AppCompatActivity {

    private Button bt;
    private Button bt_1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        initView();

        initEvent();
    }

    private void initEvent() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new Message("liaohongjie", "Second"));
            }
        });

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new Message("liaohongjie", "Second"));
            }
        });
    }

    private void initView() {
        bt = findViewById(R.id.bt);
        bt_1 = findViewById(R.id.bt_1);
    }
}
