package com.com.lhj.sample.presenter;

import android.util.Log;

import com.com.lhj.sample.constrct.ConstrctMainActivity;

import javax.inject.Inject;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

public class BasePresenter implements ConstrctMainActivity.PresenterActivity {
    @Inject
    public BasePresenter(){
        Log.e("TAG", "BasePresenter 被创建....");
    }
}
