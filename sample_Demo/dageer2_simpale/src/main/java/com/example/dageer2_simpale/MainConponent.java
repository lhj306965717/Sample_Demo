package com.example.dageer2_simpale;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by liaohongjie on 2017/9/9.
 */
@Singleton
@Component(modules = MainModel.class)
public interface MainConponent extends InjectComponent<MainActivity> {
//    void inject(MainActivity activity);
}
