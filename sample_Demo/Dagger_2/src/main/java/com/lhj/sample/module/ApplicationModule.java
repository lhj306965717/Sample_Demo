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

    /**
     * 通过添加 注解  @Provides 来产生需要的对象
     * @param sampleTest
     * @return
     */
    @Singleton
    @Provides
    public Test_1 getTest_1(SampleTest sampleTest){

        sampleTest.sample(); // 为了验证是否调用

        return new Test_1();
    }

    /**
     * 同上
     * @return
     */
    @Singleton
    @Provides
    public SampleTest getSampleTest(){
        return new SampleTest();
    }

    // 错误的写法：在被 @Provides 注解的方法中，不能有两个返回相同的对象，否则Daage2无法知道是调用哪一个
    // 即使给方法中含有参数也不行，所以每个方法只能返回不同的对象
//    @Singleton
//    @Provides
//    public SampleTest sampleTest(){
//        return new SampleTest();
//    }

}
