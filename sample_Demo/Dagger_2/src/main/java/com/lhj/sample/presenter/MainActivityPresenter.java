package com.lhj.sample.presenter;

import android.util.Log;

import com.lhj.sample.constrct.MainActivityConstrct;
import com.lhj.sample.test.Test;
import com.lhj.sample.test.Test_1;
import com.lhj.sample.test.Test_2;

import javax.inject.Inject;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

public class MainActivityPresenter implements MainActivityConstrct.PresenterActivity {

    @Inject
    Test_1 mTest_1; //通过 继承的方式 进行赋值，其实是通过 ApplicationComponet 中的

    @Inject
    public MainActivityPresenter(/*MainActivityConstrct.ViewActivity viewActivity,*/ Test test, Test_2 test2){
        Log.e("TAG", "创建ActivityPresenter.......");
    }

    // 这两个方法会按照标注顺序依次调用，会在构造方法完成，并创建了次对象后
    @Inject
    public void f(){

        //mTest_1.ff_1();

        Log.e("TAG", "f.....f");
    }
}
