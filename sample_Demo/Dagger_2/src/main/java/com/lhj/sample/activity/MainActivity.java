package com.lhj.sample.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.com.lhj.sample.R;
import com.lhj.sample.base.BaseActivity;
import com.lhj.sample.base.BaseApplication;
import com.lhj.sample.constrct.MainActivityConstrct;
import com.lhj.sample.module.DaggerMainActivityComponent;
import com.lhj.sample.module.MainActivityComponent;
import com.lhj.sample.module.MainActivityModule;
import com.lhj.sample.presenter.MainActivityPresenter;

// 这里的泛型不能用接口，必须要用实际的类型
public class MainActivity extends BaseActivity<MainActivityPresenter> implements MainActivityConstrct.ViewActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        BaseApplication baseApplication = (BaseApplication) getApplication();

        // 以嵌套的方式存在
        MainActivityComponent build = DaggerMainActivityComponent
                .builder()
                .applicationComponet(baseApplication.getTestComponet()) // 两个model对象
                .mainActivityModule(new MainActivityModule())
                .build();

        build.inject(this);

        // 创建 Test 对象
//        Test test = build.getTest();
//        test.tt();
//
//        // 创建 Test_1对象
//        Test_1 test_1 = build.getTest_1();
//        test_1.ff_1();

//        SuperTest superTest = build.getSuperTest();
//        superTest.superFF();

        /*MainActivityComponent build = DaggerComponentActivity
                .builder()
                .testActivityModule(new MainActivityModule())// 注意：这里的对象一定要创建，因为需要读取这个Module对象中被 @Provides 注解的方法
                .build();
        build.inject(this);*/
        /*Log.e("TAG", "MainActivity.....");
        Test test = build.getTest();
        test.tt();
        Test_1 test_1 = build.getTest_1();
        test_1.ff_1();*/
    }

    public void initData() {
        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String name = Thread.currentThread().getName();
                Log.e("TAG", name);
            }
        };
    }

    public void viewOnClick(View view) {

        ((Button)view).setText("点我啊");

       // Message message = handler.obtainMessage();
       // handler.sendMessage(message);
    }
}
