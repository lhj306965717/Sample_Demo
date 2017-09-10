package com.example.dageer2_simpale.test_1;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by liaohongjie on 2017/9/9.
 */

@Singleton
public class Test_1 {

    @Inject
    public Test_1() {
    }

    public void f(){
        Log.e("TAG", "调用Test_1 f 方法");
    }
}
