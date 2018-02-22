package com.lhj.sample;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lhj.sample.network.DownloadUtils;
import com.lhj.sample.new_simple.RxSimple;
import com.lhj.sample.simple.RxUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mImg = findViewById(R.id.img);
    }

    public void bt_Rx(View view){

        //        RxUtils.init_1();

//        RxUtils.init_4();
        RxSimple.init_interval();
    }

    public void bt_Rx_network(View view){

        Observable<byte[]> observable = DownloadUtils.dowloadImage("http://120.78.128.50:8081/UploadImg/HeadImg/2017/11/10/17111014522265063.jpg");
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<byte[]>() {

            @Override
            public void onCompleted() {
                Log.e("TAG", "onCompleted...");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("TAG", "onError...");
            }

            @Override
            public void onNext(byte[] bytes) {
                Log.e("TAG", "线程："+ Thread.currentThread().getName());

                Log.e("TAG", "数据大小："+bytes.length);

                mImg.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            }
        });
    }
}
