package com.example.dageer2_simpale.test_3;

import dagger.Component;

/**
 * Created by liaohongjie on 2017/9/10.
 */

@Component(modules = FlowerModule.class)
public interface FlowerComponent {

//    Flower Flower(); // 对外提供 Flower对象



    // 发现 PotComponent 创建 依赖于 FlowerComponent（所以 PotComponent 依赖于  FlowerComponent）
    PotComponent plus(PotModule potModule); // 需要 PotModule 对象来产生PotComponent对象 对外提供

    // 所以 PotModule 是需要我们手动传递进去的

    // 在这个接口中，需要知道 PotComponent 这个接口
}
