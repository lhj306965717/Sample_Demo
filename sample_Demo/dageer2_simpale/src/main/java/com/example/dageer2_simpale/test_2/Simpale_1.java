package com.example.dageer2_simpale.test_2;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by liaohongjie on 2017/9/10.
 */

public class Simpale_1 implements TestSimpale {

    @Inject
    public Simpale_1(){
        Log.e("TAG", "构造Simpale_1....");
    }
}
