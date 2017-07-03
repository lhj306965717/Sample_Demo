package com.com.lhj.sample.base;

import android.app.Application;

import com.com.lhj.sample.module.BaseComponet;
import com.com.lhj.sample.module.DaggerBaseComponet;
import com.com.lhj.sample.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

public class BaseApplication extends Application {

    @Inject
    protected BasePresenter mFPresenter;
    private BaseComponet mTestComponet;

    @Override
    public void onCreate() {
        super.onCreate();

        mTestComponet = DaggerBaseComponet.builder().build();
        mTestComponet.inject(this);
    }

    public BaseComponet getTestComponet(){
        return mTestComponet;
    }
}
