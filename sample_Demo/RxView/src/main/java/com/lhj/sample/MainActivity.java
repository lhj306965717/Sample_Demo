package com.lhj.sample;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.jakewharton.rxbinding.view.RxView;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private Button mBt;
    private RxPermissions mRxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRxPermissions = new RxPermissions(this);
        mRxPermissions.setLogging(true);

        initView();
        initEvent();
    }

    private void initEvent() {
        RxView.clicks(mBt) // clicks表示点击事件
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .compose(mRxPermissions.ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE))
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) { // 获取到了权限
                            Log.e("TAG", "权限获取成功");
                        } else { // 失败
                            Log.e("TAG", "权限获取失败");
                        }
                    }
                });
    }

    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
    }
}
