package com.lhj.sample.module;

import com.lhj.sample.activity.MainActivity;
import com.lhj.sample.scope.ActivityScoped;
import com.lhj.sample.test.Test;
import com.lhj.sample.test.Test_1;

import dagger.Component;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */


/**
 * @Scope的作用: 它的作用只是保证依赖在@Component中是唯一的，可以理解为“局部单例”。
 * @Scope是需要成对存在的，在Module的Provide方法中使用了@Scope， 那么对应的Component中也必须使用@Scope注解，当两边的@Scope名字一样时（比如同为@Singleton）,
 * 那么该Provide方法提供的依赖将会在Component中保持“局部单例”。
 * 而在Component中标注@Scope，provide方法没有标注，那么这个Scope就不会起作用，
 * 而Component上的Scope的作用也只是为了能顺利通过编译，就像我刚刚定义的ActivityScope一样。
 * <p>
 *
 * 注意：
 * @Singleton也是一个自定义@Scope，它的作用就像上面说的一样。 但由于它是Dagger2中默认定义的，所以它比我们自定义Scope对了一个功能，
 * 就是编译检测，防止我们不规范的使用Scope注解，仅此而已。
 *
 *
 * @Component关联的@Module中的任何一个@Provides有@scope，则该整个@Component要加上这个scope
 *
 *
 * @Component的dependencies与@Component自身的scope不能相同，即组件之间的scope不能相同（最典型的就是当前@Component 不能使用@Singleton，而只能使用@ActivityScoped）
 */

// 通常在Android中用来标记在App整个生命周期内存活的实例
//@Singleton // 表示只创建一次对象，但是这个一次只是在 Dagger2的生命周期中来说是只创建一次
@ActivityScoped // 这里必须标注@Scope注解　@ActivityScoped是自定义的@Scope，不能直接添加@Singleton（应该说是 model中的@ActivityScoped注解要与这个相匹配才行）
@Component(dependencies = ApplicationComponet.class, modules = MainActivityModule.class)
// 这里保证了　ApplicationComponet　在　MainActivityComponent是唯一的，局部单列
public interface MainActivityComponent {

    void inject(MainActivity activity); // 这里也不能传入接口，必须是传入的具体类型

    // 对外暴漏
    Test getTest();  // 这里的方法是被可以被调用，只要拿到这个接口的代理对象

    // 可以直接对外暴露上一层的 ApplicationComponet 中的对象，如果不加@Singleton 会调用 ApplicationModule 中的  @Provides 方法
    Test_1 getTest_1();
}
