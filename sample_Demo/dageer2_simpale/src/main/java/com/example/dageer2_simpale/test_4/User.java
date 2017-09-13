package com.example.dageer2_simpale.test_4;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by liaohongjie on 2017/9/13.
 */

public class User {

    @Inject
    public User(){
        Log.e("TAG", "构造User...");
    }
}
