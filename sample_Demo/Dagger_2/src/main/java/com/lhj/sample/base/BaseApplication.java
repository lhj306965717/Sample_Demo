package com.lhj.sample.base;

import android.app.Application;
import android.util.Log;

import com.lhj.sample.module.ApplicationComponet;
import com.lhj.sample.module.DaggerApplicationComponet;
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

        // 这样写  ApplicationPresenter 是并没有被注解上去的
        mTestComponet = DaggerApplicationComponet.builder().build();

        mTestComponet.inject(this);

        if(mFPresenter != null)
            Log.e("TAG", "ApplicationPresenter 不为null");
        else
            Log.e("TAG", "ApplicationPresenter 为null");

    }

    public ApplicationComponet getTestComponet(){
        return mTestComponet;
    }
}
