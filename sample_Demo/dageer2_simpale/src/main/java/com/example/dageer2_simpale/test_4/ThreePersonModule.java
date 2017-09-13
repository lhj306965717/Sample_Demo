package com.example.dageer2_simpale.test_4;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liaohongjie on 2017/9/11.
 */

@Module
public class ThreePersonModule {

    private final ThreeActivity mActivity;

    public ThreePersonModule(ThreeActivity activity){
        this.mActivity = activity;
    }

    @Provides
    public ThreeActivity providesThreeActivity(){
        return mActivity;
    }
}
