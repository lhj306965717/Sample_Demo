package com.lhj.sample.network;


import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by liaohongjie on 2017/12/15.
 */

public class DownloadUtils {

    private static OkHttpClient mOkHttpClient;

    static {
        if(mOkHttpClient == null){
            mOkHttpClient = new OkHttpClient();
        }
    }

    public static Observable<byte[]> dowloadImage(final String url) {
        return Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(final Subscriber<? super byte[]> subscriber) {

                Log.e("TAG", "线程："+ Thread.currentThread().getName());

                if (!subscriber.isUnsubscribed()) {
                    Request request = new Request.Builder().url(url).build();
                    mOkHttpClient
                            .newCall(request)
                            .enqueue(new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    subscriber.onError(e);
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    byte[] bytes = response.body().bytes();

                                    int code = response.code();

//                                    Log.e("TAG", "数据："+new String(bytes, "UTF-8"));
                                    Log.e("TAG", "code: "+code + "   "+  response.request().url().url().toString());

                                    if(bytes != null){
                                        /** 在这里通过 把请求的数据 发射给 订阅者 */
                                        subscriber.onNext(bytes);
                                    }

                                    subscriber.onCompleted();
                                }
                            });
                }
            }
        });
    }

}
