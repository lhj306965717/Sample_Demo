package com.lhj.sample.http;

import android.net.Uri;
import android.util.Log;

import com.lhj.sample.base.BaseApplication;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by liaohongjie on 2017/9/15.
 */

public class RequestUtils {

    public static void getSync(String url) throws IOException {
        OkHttpClient okHttpClient = BaseApplication.getOkHttpClient();

        Request.Builder requestBuilder = new Request
                .Builder()
                .url(url);

        Request request = requestBuilder.build();

        Call call = okHttpClient.newCall(request);

        // 真正的发起请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

        {
            // 真正的发起请求， 两个都是一样的发起请求，
            // 只是写法是不一样
//            Response response = call.execute();
//            boolean successful = response.isSuccessful();
//
//            if(successful){
//
//                // 请求成功
//
//            }else{
//
//                // 请求失败
//            }
        }
    }

    public static void getSync(String url, Map<String, String> args) throws IOException {

        OkHttpClient okHttpClient = BaseApplication.getOkHttpClient();

        Uri.Builder buildUpon = Uri.parse(url).buildUpon();

        Set<String> set = args.keySet();

        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()) {

            String key = iterator.next();

            buildUpon.appendQueryParameter(key, args.get(key));
        }

        url = buildUpon.build().toString();

        Log.e("TAG", "参数：" + url);

        Request.Builder requestBuilder = new Request
                .Builder()
                .url(url);

        Request request = requestBuilder.build();

        Call call = okHttpClient.newCall(request);

        Response execute = call.execute();

        if (execute.isSuccessful()) {

            // 请求成功

        } else {

            // 请求失败
        }
    }
}
