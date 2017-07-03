package com.lhj.sample;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tbruyelle.rxpermissions.RxPermissions;

import io.rx_cache2.internal.RxCache;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

public class MainActivity extends AppCompatActivity {

    private RxCache mRxCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxPermissions rxPermissions = new RxPermissions(this);

        Observable.empty()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxPermissions.ensure(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE))
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {

                    }
                });
                /*.subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {

                            Log.e("TAG", " 获取到数据");

                            File file = new File(Environment.getExternalStorageDirectory(), "liaohongjie.txt");

                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (file.exists()) {
                                Log.e("TAG", "文件存在....");
                            }

                            Log.e("TAG", file.getAbsolutePath());

                            mRxCache = new RxCache.Builder().persistence(file, new GsonSpeaker());

                            CacheProviders providers = mRxCache.using(CacheProviders.class);

                            providers.insertUserBean(new UserBean("廖红杰", 24));

                        }
                    }
                });*/
    }
}
