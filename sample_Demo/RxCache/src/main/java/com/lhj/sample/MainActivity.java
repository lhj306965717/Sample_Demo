package com.lhj.sample;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.rx_cache.internal.RxCache;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private RxCache mRxCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxPermissions rxPermissions = new RxPermissions(this);

        View view = findViewById(R.id.bt);

        RxView.clicks(view)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .compose(rxPermissions.ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE))
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) { // 获取到了权限
                            if (aBoolean) {

                                Log.e("TAG", " 获取到数据");

                                File file = new File(Environment.getExternalStorageDirectory(), "liaohongjie.txt");

                                try {
                                    file.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                Log.e("TAG", file.getAbsolutePath());

                                if (file.exists()) {
                                    mRxCache = new RxCache.Builder().persistence(file, new GsonSpeaker());

                                    CacheProviders providers = mRxCache.using(CacheProviders.class);

                                    providers.insertUserBean(new UserBean("廖红杰", 24));
                                }
                            }
                        } else { // 失败
                            Log.e("TAG", "权限获取失败");
                        }
                    }
                });

    }
}
