package com.lhj.sample.presenter;

import android.util.Log;

import com.lhj.sample.constrct.MainActivityConstrct;

import javax.inject.Inject;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

public class ApplicationPresenter implements MainActivityConstrct.PresenterActivity {
    @Inject
    public ApplicationPresenter(){
        Log.e("TAG", "ApplicationPresenter 被创建....");
    }
}
