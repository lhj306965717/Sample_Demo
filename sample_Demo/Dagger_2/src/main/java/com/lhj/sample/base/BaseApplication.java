package com.lhj.sample.base;

import android.app.Application;

import com.lhj.sample.module.ApplicationComponet;
import com.com.lhj.sample.module.DaggerBaseComponet;
import com.lhj.sample.presenter.ApplicationPresenter;

import javax.inject.Inject;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

public class BaseApplication extends Application {

    @Inject
    protected ApplicationPresenter mFPresenter;
    private ApplicationComponet mTestComponet;

    @Override
    public void onCreate() {
        super.onCreate();

        mTestComponet = DaggerBaseComponet.builder().build();
        mTestComponet.inject(this);
    }

    public ApplicationComponet getTestComponet(){
        return mTestComponet;
    }
}
