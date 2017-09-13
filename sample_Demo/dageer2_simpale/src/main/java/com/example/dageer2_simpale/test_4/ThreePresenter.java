package com.example.dageer2_simpale.test_4;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by liaohongjie on 2017/9/11.
 */

public class ThreePresenter {

    @Inject
    Person mPerson;

    @Inject
    public ThreePresenter(/**ThreeActivity activity*/User user) { // 像 这样需要的参数不在 xxxModule 中提供也是可以依赖注入的
        Log.e("TAG", " 构造 ThreePresenter ... ");
    }

    @Inject
    public void init() {
        if (mPerson == null) {
            Log.e("TAG", " mPerson 等于 null  ----");
        } else {
            Log.e("TAG", " mPerson 不等于 null  ----");
        }
    }
}
