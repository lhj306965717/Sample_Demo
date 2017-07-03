package com.lhj.sample.module;

import com.lhj.sample.test.SampleTest;
import com.lhj.sample.test.Test_1;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

@Module
public class ApplicationModule {

    @Singleton
    @Provides
    public Test_1 getTest_1(SampleTest sampleTest){

        sampleTest.sample();

        return new Test_1();
    }

    @Singleton
    @Provides
    public SampleTest getSampleTest(){
        return new SampleTest();
    }
}
