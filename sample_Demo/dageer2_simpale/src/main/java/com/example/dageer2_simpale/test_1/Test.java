package com.example.dageer2_simpale.test_1;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by liaohongjie on 2017/9/9.
 */

public class Test {

    /**
     * 像这种在构造器中需要其他独享依赖的，成为 依赖倒置，需要先 创建 Test_1依赖对象才行
     * 且这种 依赖倒置 无法适用于 构造函数中的 参数对象是 接口或者抽象类
     * 如果是接口或者抽象类的，需要使用 Module 依赖
     */
    @Inject
    public Test(Test_1 test_1) {
        Log.e("TAG", "构建Test");
        test_1.f();
    }
}
