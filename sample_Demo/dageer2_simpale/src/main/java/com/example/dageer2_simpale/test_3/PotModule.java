package com.example.dageer2_simpale.test_3;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liaohongjie on 2017/9/10.
 */

@Module
public class PotModule {

    @Provides
    Pot providePot(Flower flower) {
        return new Pot(flower);
    }

}
