package com.lhj.sample.module;

import android.util.Log;

import com.lhj.sample.test.Test;
import com.lhj.sample.test.Test_1;
import com.lhj.sample.test.Test_2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */

@Module
public class MainActivityModule {

/*    private final MainActivityConstrct.ViewActivity mViewActivity;

    public MainActivityModule(MainActivityConstrct.ViewActivity viewActivity){
        this.mViewActivity = viewActivity;
    }

    @Provides
    public MainActivityConstrct.ViewActivity providesConstrctActivity(){
        return mViewActivity;
    }*/

    //@Singleton
    @Provides
    public Test providesTest() {
        //  // 注意：这个　providesTest　方法 如果被多次调用，那么会被次创建对象
        // 但是如果在方法上面加上 @Singleton， 那么返回的对象只会是同一个，意思是只会创建一个对象，
        // 下次再调用的时候还是返回是第一次创建的对象
        return new Test();
    }

    @Provides // 写法错误
    // 注意：标识有@Provides的方法的返回值不能有相同的，如果有相同的会无法区分，导致报错，所以返回值不能是一样的，且必须要有返回值
    public Test_2 providesTest_1(Test_1 test_1) {
        test_1.ff_1();
        Log.e("TAG", "providesTest_1.....");

        return new Test_2();
    }
}
