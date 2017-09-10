package com.example.dageer2_simpale.test_1;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liaohongjie on 2017/9/9.
 */

@Module
public class MainModel {

//    @Singleton
    @Provides
    public Test provideTest(Test_1 test_1){
        return new Test(test_1);
    }

}
