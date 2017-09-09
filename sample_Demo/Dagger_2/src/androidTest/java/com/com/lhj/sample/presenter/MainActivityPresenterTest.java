package com.com.lhj.sample.presenter;

import com.lhj.sample.presenter.MainActivityPresenter;
import com.lhj.sample.test.Test_2;

import org.junit.Before;

/**
 * Created by LiaoHongjie on 2017/7/1.
 */
public class MainActivityPresenterTest {
    private MainActivityPresenter mPresenter;

    @Before
    public void setUp() throws Exception {

        mPresenter = new MainActivityPresenter(new com.lhj.sample.test.Test(), new Test_2());
    }

}