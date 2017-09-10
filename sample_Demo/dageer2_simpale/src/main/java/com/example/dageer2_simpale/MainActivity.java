package com.example.dageer2_simpale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.dageer2_simpale.test_1.MainConponent;
import com.example.dageer2_simpale.test_1.Test;
import com.example.dageer2_simpale.test_2.DaggerSimpaleConponent;
import com.example.dageer2_simpale.test_2.Qualifier.QualiflerSimpale_2;
import com.example.dageer2_simpale.test_2.TestSimpale;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Test mTest;

/**
 有两种方式构造，1.通过在@Component上设置module    2.通过在构造器上添加@Iject

 注意：如果不在 @Component上设置module， 那么被注入创建的对象的构造函数上必须标注 @Iject

 @Inject可以标注在 属性变量、构造函数、public方法上
 */


/**
 @Component Component中一般使用两种方式定义方法:

 1. void inject(目标类  obj);Dagger2会从目标类开始查找@Inject注解，自动生成依赖注入的代码，调用inject可完成依赖的注入。

 2. Object getObj(); 如：Pot getPot();
 Dagger2会到Pot类中找被@Inject注解标注的构造器，自动生成提供Pot依赖的代码，这种方式一般为其他Component提供依赖。（一个Component可以依赖另一个Component，后面会说）
 */

    /**
     * @Module是告诉Component，可以从这里获取依赖对象。Component就会去找被@Provide标注的方法，相当于构造器的@Inject，可以提供依赖。
     *
     * 还有一点要说的是，@Component可以指定多个@Module的，如果需要提供多个依赖的话。并且Component也可以依赖其它Component存在。
     *
     * 注意：@Module需要和@Provide是需要一起使用的时候才具有作用的，并且@Component也需要指定了该Module的时候。
     */


    /**
     * 注意：与上面的 Test 不能一起测试使用，因为没有提供上面Test的依赖，会报错。
     */


//    @Named不能写在 @Component 上，反正就是哪里需要依赖对象就写在哪里。如果像是倒置依赖的方式，那么就写在参数那里
//    @Named("QualiflerSimpale_2") // 使用 QualiflerSimpale_2 提供依赖
    @QualiflerSimpale_2 // 使用 @Qualifler 标注
    @Inject
    TestSimpale mSimpale;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         /**  test_1 包 单独测试  */
        //  DaggerMainConponent.builder().build().inject(this);



      /**  test_2 包 单独测试  */
        // 下面这两种方式都可以注入的区别：
        // 第一种方式不需要创建module对象，是因为 提供依赖的 Module 的构造函数是默认的构造函数，如果检测为null会自动创建
        // 如果是有参构造函数则会构造失败
//        DaggerSimpaleConponent.builder().build().inject(this);
//        DaggerSimpaleConponent.builder().simpaleModel(new SimpaleModel()).build().inject(this);


        /**  test_1  和  test_2 包 嵌套 一起测试  */

        // 注意：DaggerMainConponent 别导错包，否则报错
        MainConponent mainConponent = com.example.dageer2_simpale.test_1.DaggerMainConponent.builder().build();

        DaggerSimpaleConponent
                .builder()
                .mainConponent(mainConponent)
                .build()
                .inject(this);


        /**  注意：上面进行测试的使用，需要分别 注释掉一部分

         特别是是进行第二次测试需要  注释掉
         @Inject Test mTest;

         */
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mTest == null)
            Log.e("TAG", "Test 为 null");
        else
            Log.e("TAG", "Test 不为 null");


        if (mSimpale == null)
            Log.e("TAG", "mSimpale 为 null");
        else
            Log.e("TAG", "mSimpale 不为 null");
    }
}
