package com.lhj.junit;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;



/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest {

    /**
     * 测试用例类需要使用注解：@MediumTest和@RunWith(AndroidJUnit4.class)
     *我们所写的测试用例方法需要添加名称为Test的注解，否则的话，就找不到测试方法
     *
      测试函数需要添加@Test注解
     *
     */
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        // 测试包名是否一样
        assertEquals("com.lhj.junit", appContext.getPackageName());
    }


    // 测试UI，以及Activity

    /**
     * 这句话就定义了一个测试规则,可以看到构造方法的参数里指定了一个 MainActivity.class,
     * 具体的体现就是当你运行这段测试代码时,app将会直接打开 MainActivity界面然后进行你所定义的测试用例.
     * 所以当你想直接测试某个界面时,你可以把那个界面填到这个参数里,这样就直接打开你指定的界面进行测试了.
     *
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void sayHellow(){

        // perform 是执行的意思。执行 click 点击事件
        onView(withId(R.id.bt)).perform(click());

        // 判断 这个 bt 这个View 是否在 DecorView布局上
        // onView(withId(R.id.bt)).inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())));

        onView(withId(R.id.bt)).inRoot(withDecorView(not((View)mActivityRule.getActivity().mTv)));

        // onView(withId(R.id.bt)).withFailureHandler()

        //String str = "liaohongjie";
        // 这个期望显示的文本，最后TextView上显示的内容一定要与这个（str）相匹配(matches)才是正确的
        // 因为括号里面使用的是 matches， check 是检查的意思
        //onView(withId(R.id.tv)).check(matches(withText(str)));

    }
}
