package com.com.lhj.sample.base;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

public class BaseActivity<T> extends AppCompatActivity {

    @Inject
    protected T mPresenter;
}
