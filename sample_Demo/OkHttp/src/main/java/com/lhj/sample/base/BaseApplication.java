package com.lhj.sample.base;

import android.app.Application;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.lhj.sample.http.Https;
import com.lhj.sample.interceptor.RequestInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * Created by liaohongjie on 2017/9/12.
 */

public class BaseApplication extends Application {

    private static OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();

        initOkHttp();
    }

    public void initOkHttp() {

        File cache = getExternalCacheDir();
        int cacheSize = 10 * 1024 * 1024;

        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(getApplicationContext()));

        Https.SSLParams sslParams = Https.getSslSocketFactory(null, null, null);

        mOkHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(15, TimeUnit.SECONDS) // 连接超时时间，秒
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .pingInterval(15, TimeUnit.SECONDS) // webSocket 轮训隔间，单位秒
                .cache(new Cache(cache.getAbsoluteFile(), cacheSize))
                .cookieJar(cookieJar) // Cookies 持久化
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        // Android SSL 验证服务器证书 Hostname 不匹配错误。
                        // 进行 忽略hostname 的验证
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager) // https配置
                .addInterceptor(new RequestInterceptor()) // 设置拦截器
                .build();

    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
