package com.lhj.sample;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jakewharton.rxbinding.view.RxView;
import com.lhj.sample.bean.UserBean;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.rx_cache.internal.RxCache;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

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
                                try {
                                    File file = new File(Environment.getExternalStorageDirectory(), "liaohongjie.txt");
                                    file.createNewFile();

                                    Log.e("TAG", file.getAbsolutePath());

                                    if (file.exists()) {

                                      //  diskCache();

                                        cacheProviders(file);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else { // 失败
                            Log.e("TAG", "权限获取失败");
                        }
                    }
                });
    }

    private void diskCache(){
        // 磁盘缓存
        DiskCache diskCache = new DiskCache(getApplicationContext(), "liaohongjie");
        String key = "https:zhiyicx.com";
        //diskCache.wirteData(key, "廖红杰阿娇可达扩大进口量大啊啊看见点解啊扣篮对决啊啊看见打开了较大 啊贷记卡了");

        String data = diskCache.readData(key);

        Log.e("TAG", "读取数据："+data);
    }

    private void cacheProviders(File cacheDir){
        CacheProviders providers = new RxCache.Builder().persistence(cacheDir, new GsonSpeaker()).using(CacheProviders.class);
        providers.insertUserBean(new UserBean("廖红杰", 24));
    }
}
