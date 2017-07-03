package com.com.lhj.sample.module;

import com.com.lhj.sample.test.Test_1;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

@Module
public class BaseModule {

    @Provides
    public Test_1 getTest_1(){
        return new Test_1();
    }
}
