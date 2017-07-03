package com.com.lhj.sample.module;

import android.app.Application;

import com.com.lhj.sample.test.Test_1;

import dagger.Component;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

@Component(modules = BaseModule.class)
public interface BaseComponet {

    void inject(Application application);

    Test_1 Test_1(); //对外暴露 Test_1
}
