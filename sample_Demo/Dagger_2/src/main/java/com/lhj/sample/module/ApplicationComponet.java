package com.lhj.sample.module;

import android.app.Application;

import com.lhj.sample.test.Test_1;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponet {

    void inject(Application application);

    Test_1 Test_1(); //对外暴露 Test_1
}
