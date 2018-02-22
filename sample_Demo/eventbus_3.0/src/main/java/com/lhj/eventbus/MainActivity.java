package com.lhj.eventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button bt;
    private Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true) // 处理粘性事件还需要设置
    public void onMoonEvent(Message message) {

        if ("Second".equals(message.getmTag())) {
            tv.setText(message.getMessage());
        }
    }

    private void initEvent() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().register(MainActivity.this);
            }
        });
    }

    private void initView() {
        tv = findViewById(R.id.tv);
        bt = findViewById(R.id.bt);
        bt_register = findViewById(R.id.bt_register);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
