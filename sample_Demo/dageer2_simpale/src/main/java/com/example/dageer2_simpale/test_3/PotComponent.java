package com.example.dageer2_simpale.test_3;

import dagger.Subcomponent;

/**
 * Created by liaohongjie on 2017/9/10.
 */

//@Component(dependencies = FlowerComponent.class, modules = PotModule.class)

@Subcomponent(modules = PotModule.class)
public interface PotComponent {

//    Pot getPot();

    //  SecodeActivityComponent  的创建  依赖于  PotComponent
    SecodeActivityComponent plus(); // 拿到SecodeActivityComponent 接口对象后，调用inject方法注入依赖

    // 在这个接口中 需要知道  SecodeActivityComponent 接口
    // 发现每个接口中，都需要知道一个接口，有点耦合了（两两联系太紧密）

}
