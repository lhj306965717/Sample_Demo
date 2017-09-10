package com.example.dageer2_simpale;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dageer2_simpale.test_3.DaggerFlowerComponent;
import com.example.dageer2_simpale.test_3.Pot;
import com.example.dageer2_simpale.test_3.PotModule;

import javax.inject.Inject;

/**
 * Created by liaohongjie on 2017/9/10.
 *
 * @Component的dependence和@SubComponent 测试
 */

/**
 * @Component的dependence和@SubComponent 测试
 *
 */

/**
 * Component可以依赖于其他Component，可以使用@Component的dependence，
 * 也可以使用@SubComponent，这样就可以获取其他Component的依赖了
 *
 */

public class SecodeActivity extends AppCompatActivity {

    @Inject
    Pot pot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        DaggerSecodeActivityComponent.builder()
//                .potComponent(DaggerPotComponent
//                        .builder()
//                        .flowerComponent(DaggerFlowerComponent.create())
//                        .build())
//                .build()
//                .inject(this);


        /**
         *
         * 那什么时候该用@Subcomponent呢？
         * Subcomponent是作为Component的拓展的时候
         *
         * */
        /**
         *
         * Component dependencies 能单独使用，而Subcomponent必须由Component调用方法获取。
         *
         * */

        // 通过 依赖注入的方式 ，发现 是 跟 @Component 的 方式相反
        // 从顶层的  Component 接口开始依赖，倒置的方式
        DaggerFlowerComponent.create()
                .plus(new PotModule())  // 这个方法返回PotComponent
                .plus()                 // 这个方法返回MainActivityComponent
                .inject(this);

        if(pot == null)
            Log.e("TAG", " pot 为 null");
        else
            Log.e("TAG", "pot 不为null ");

    }

}
