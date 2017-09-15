package com.lhj.retrofit2.base;

import android.app.Application;

import com.lhj.retrofit2.http.HttpURL;
import com.lhj.retrofit2.interceptor.RequestInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by liaohongjie on 2017/9/12.
 */

public class BaseApplication extends Application {

    private static Retrofit mRetrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        initOkHttp();
    }

    public void initOkHttp() {

        File cache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;

        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(15, TimeUnit.SECONDS) // 连接超时时间，秒
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .pingInterval(15, TimeUnit.SECONDS) // webSocket 轮训隔间，单位秒
                .cache(new Cache(cache.getAbsoluteFile(), cacheSize))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        // Android SSL 验证服务器证书 Hostname 不匹配错误。
                        // 进行 忽略hostname 的验证
                        return true;
                    }
                })
                .addInterceptor(new RequestInterceptor()) // 设置拦截器
                .build();

        // 使用Scalars
        mRetrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())// 使用Scalars
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(HttpURL.URL)
                .build();

    }

    public static Retrofit getRetrofit() {
        return mRetrofit;
    }
}
