package com.lhj.sample.test;

import android.util.Log;

import com.lhj.sample.scope.ActivityScoped;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/9/7.
 */

@ActivityScoped
public class SuperTest {

    @Inject
    public SuperTest(){
        Log.e("TAG", "创建超级Test");
    }

    public void superFF(){
        Log.e("TAG", "调用超级FF.....");
    }

}
