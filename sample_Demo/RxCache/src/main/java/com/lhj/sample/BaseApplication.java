package com.lhj.sample;

import android.app.Application;
import android.content.Context;

/**
 * Created by LiaoHongjie on 2017/7/4.
 */

public class BaseApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
