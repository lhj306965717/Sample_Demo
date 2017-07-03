package com.com.lhj.sample.module;

import com.com.lhj.sample.activity.MainActivity;
import com.com.lhj.sample.test.Test;
import com.com.lhj.sample.test.Test_1;

import dagger.Component;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

// 通常在Android中用来标记在App整个生命周期内存活的实例
//@Singleton // 表示只创建一次对象，但是这个一次只是在 Dagger2的生命周期中来说是只创建一次

@Component(dependencies = BaseComponet.class, modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity activity); // 这里也不能传入接口，必须是传入的具体类型

    // 对外暴漏
    Test getTest();  // 这里的方法是被可以被调用，只要拿到这个接口的代理对象

    // 可以直接对外暴露上一层的 BaseComponet 中的对象，如果不加@Singleton 会调用 BaseModule 中的  @Provides 方法
    Test_1 getTest_1();
}
