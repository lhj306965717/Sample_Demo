package com.example.dageer2_simpale.test_4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by liaohongjie on 2017/9/11.
 */

public class ThreeActivity extends AppCompatActivity {

    @Inject
    Person mPerson;
    @Inject
    ThreePresenter mThreePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /**
         *
         * 为什么要使用 Module 来提供需要的对象值？
         *
         * 1.首先依赖注入有两种方式：
         *   (1).构造函数标记@Inject的方式
         *   (2).通过Module方式提供
         * 2.通过Module来提供一般是当前类的构造函数无法使用@Inject来标记
         *   最典型的比如：Activity类、第三方类等，这时候就需要使用 Module 来提供对象值
         * */

        DaggerPersonComponent
                .builder()
//                .threePersonModule(new ThreePersonModule(this))
                .build()
                .inject(this);


        // 用来测试只有 @InjectPerson mPerson; 时候的依赖注入
//        DaggerPersonComponent.create().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mPerson == null) {
            Log.e("TAG", " +++  mPerson 等于 null  ");
        } else {
            Log.e("TAG", " +++  mPerson 不等于 null  ");
        }
    }
}
