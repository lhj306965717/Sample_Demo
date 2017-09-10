package com.example.dageer2_simpale.test_3;

import com.example.dageer2_simpale.SecodeActivity;

import dagger.Subcomponent;

/**
 * Created by liaohongjie on 2017/9/10.
 */

//@Component(dependencies = PotComponent.class)
@Subcomponent
public interface SecodeActivityComponent {

    void inject(SecodeActivity activity);

}
