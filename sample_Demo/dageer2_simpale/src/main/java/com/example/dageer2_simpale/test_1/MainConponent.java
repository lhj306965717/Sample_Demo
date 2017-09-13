package com.example.dageer2_simpale.test_1;

import com.example.dageer2_simpale.TestActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by liaohongjie on 2017/9/9.
 */
@Singleton // 嵌套依赖时所必须添加的
@Component/*(modules = MainModel.class)*/
public interface MainConponent {

//    void inject(MainActivity activity);  // 注释掉的原因是 两个包的 inject的参数是一样的，重复了，第三次 嵌套依赖 会报错

    void inject(TestActivity activity); // 写这个 inject的 原因是 为了 测试 是否 是 上面一行 参数重复 导致嵌套依赖报错的原因


    // Test 有两种方式提供依赖， 1. MainModel 方式    2. 在 Test 的构造函数上标注 @Inject
    Test getTest();
}
