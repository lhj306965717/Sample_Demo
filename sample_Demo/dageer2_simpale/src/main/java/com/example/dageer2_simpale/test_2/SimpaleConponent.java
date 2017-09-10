package com.example.dageer2_simpale.test_2;

import com.example.dageer2_simpale.InjectComponent;
import com.example.dageer2_simpale.MainActivity;
import com.example.dageer2_simpale.scoped.ActivityScoped;
import com.example.dageer2_simpale.test_1.MainConponent;
import com.example.dageer2_simpale.test_2.Qualifier.QualiflerSimpale_1;

import dagger.Component;

/**
 * Created by liaohongjie on 2017/9/10.
 */

@ActivityScoped // 这里添加注解的原因是 嵌套 依赖必须添加作用域，否则报错
@Component(dependencies = MainConponent.class, modules = SimpaleModel.class)
public interface SimpaleConponent extends InjectComponent<MainActivity>{

    void inject(MainActivity activity);


    // 注意：如果这里要提供对象，那么还是需要标注使用哪一个来提供依赖
    @QualiflerSimpale_1
    TestSimpale getTestSimpale();
}
