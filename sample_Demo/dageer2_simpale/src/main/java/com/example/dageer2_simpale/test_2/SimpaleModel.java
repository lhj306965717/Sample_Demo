package com.example.dageer2_simpale.test_2;

import android.util.Log;

import com.example.dageer2_simpale.test_2.Qualifier.QualiflerSimpale_1;
import com.example.dageer2_simpale.test_2.Qualifier.QualiflerSimpale_2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liaohongjie on 2017/9/10.
 */

@Module
public class SimpaleModel {


    // 像这种有两个以上提供相同的依赖对象，不添加@Named就会报错，因为不知道需要哪一个方法提供依赖

//    而@Qualifier的作用和@Named是完全一样的，不过更推荐使用@Qualifier，因为@Named需要手写字符串，容易出错。
    // @Qualifier不是直接注解在属性上的，而是用来自定义注解的

//    @Named("QualiflerSimpale_1")
    @QualiflerSimpale_1
    @Provides
    public TestSimpale providesSimpale_1(){

        Log.e("TAG", " 调用 providesSimpale_1...");

        return new Simpale_1();
    }

//    @Named("QualiflerSimpale_2")
    @QualiflerSimpale_2
    @Provides
    public TestSimpale providesSimpale_2(){

        Log.e("TAG", " 调用 providesSimpale_2...");

        return new Simpale_2();
    }
}
