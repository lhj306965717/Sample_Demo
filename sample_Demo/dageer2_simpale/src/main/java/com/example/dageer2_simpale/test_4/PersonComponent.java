package com.example.dageer2_simpale.test_4;

import dagger.Component;

/**
 * Created by liaohongjie on 2017/9/11.
 */

@Component(modules = ThreePersonModule.class)
public interface PersonComponent {

    void inject(ThreeActivity activity);
}
