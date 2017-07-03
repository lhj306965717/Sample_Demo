package com.lhj.sample;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initTest_1();
        // initTest_2();

        // 操作符

        //initTest_3();
        //initTest_4();
        //  initTest_5();

        // initTest_6();

    }

    private void initTest_6() {


        /*
        * 涉及两个比较核心的方法subscribeOn和observeOn这两个方法都传入一个Scheduler对象，subscribeOn指定发射事件的线程，observeOn指定消费事件的线程。
         在2.x的API中仍然支持主要的默认scheduler: computation, io, newThread 和 trampoline，可以通过io.reactivex.schedulers.Schedulers这个实用的工具类来调度。

         我们在android中主要就使用下边这两个就够了：
         ①Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
         ②AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
        * */


        // 简单例子
        Flowable<String> obserable = Flowable.just("Hello,I am China!");

        obserable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, s);
                    }
                });

    }

    private void initTest_5() {
        ArrayList<String[]> list = new ArrayList<>();
        String[] words1 = {"Hello,", "I am", "China!"};
        String[] words2 = {"Hello,", "I am", "Beijing!"};
        list.add(words1);
        list.add(words2);
        Flowable.fromIterable(list)
                .flatMap(new Function<String[], Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(String[] strings) throws Exception {

                        Log.e(TAG, Arrays.asList(strings).toString());

                        // 这里返回的 又是一个数据发射源，相当于以一对多的转换，
//                        Flowable.fromArray(strings);

                        return Flowable.fromArray(strings);
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, s);
                    }
                });

        /*
        * flatMap和map还是有共同点的，都是将一个对象转换为另一个对象，
        * 不同的是map只是一对一的转换，而flatMap可以是一对多的转换，并且是转换为另外一个Flowable对象！
        * */
    }

    private void initTest_4() {
        Flowable.just("Hello,I am China!")

                //需要注意的问题：
                // 将1.x中的Func1,2改为Function和BiFunction，Func3-9改为Function3-9
                //多参数FuncN改为Function<Object[],R>


                //这个第一个泛型为接收参数的数据类型，第二个泛型为转换后要发射的数据类型
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {

                        // 从数据发射到 数据接收过程中的一个变换操作

                        Log.e(TAG, s);

                        return s + "__by Mars";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        // 这里面就是接收，即监听者

                        Log.e(TAG, s);
                    }
                });
    }

    private void initTest_3() {

        // 用 just 与 from都可以创建数据 发射源(被监听者)

        /*
        * 需要注意的问题：在1.x的API中，这里是Action1，在2.x中使用Consumer来代替，
        * 如果是两个参数，则用BiConsumer来代替Action2，而且在2.x中删除了Action3-9，
        * 如果是多个参数则用Custom<Object[]>代替ActionN。
        * */

        Flowable.just("Hello,I am China!")
                //替代1.x中的action1,接收一个参数，如果是两个参数action2使用BiCustomer，而且删除了action3-9
                //多个参数用Custom<Object[]>
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {

                        Log.e(TAG, s);
                    }
                });

        /*
        * RxJava还有一个API能达到类似的效果，就是from()，
        * 但是因为在使用java8编译时，javac不能够区分功能接口类型，
        * 所以它在2.x中被拆分为：fromArray,fromIterable,fromFuture
        * */

        // 所以上边又可以这样写：
        Flowable.fromArray("Hello,I am China!")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e("consumer", s);
                    }
                });
    }

    private void initTest_2() {
        //创建订阅者, 即观察者(监听者)
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
//这一步是必须，我们通常可以在这里做一些初始化操作，调用request()方法表示初始化工作已经完成
                //调用request()方法，会立即触发onNext()方法
                //在onComplete()方法完成，才会再执行request()后边的代码

                /*
                * 在2.x中，我们在onSubscribe()回调中必须调用s.request()方法去请求资源，
                * 参数就是要请求的数量，一般如果不限制请求数量，可以写成Long.MAX_VALUE，之后会立即触发onNext()方法！
                * 所以当你在onSubscribe()/onStart()中做了一些初始化的工作，而这些工作是在request()后面时，会出现一些问题，在onNext()执行时，你的初始化工作的那部分代码还没有执行。
                * 为了避免这种情况，请确保你调用request()时，已经把所有初始化工作做完了。
                * */

                Log.e(TAG, "onSubscribe");

                s.request(5000); // 如果不设置请求次数，那么就不会回调  onNext 方法，
            }

            @Override
            public void onNext(String o) {
                Log.e(TAG, o);

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }

        };

        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> e) throws Exception {

                e.onNext("新世界");

            }
        }, BackpressureStrategy.BUFFER);  // BackpressureStrategy是2.0中支持的 背压

        flowable.subscribe(subscriber);
    }

    private void initTest_1() {

        // 定义一个 观察者，即数据接收源
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //  onSubscribe(Disposable d)这个回调方法是在2.x中添加的，
                // Dispose参数是由1.x中的Subscription改名的，为了避免名称冲突！
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        };

        // 创建一个被观察者， 数据发射源
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

                e.onNext("廖红杰你好!");
                e.onNext("廖红杰你好!");

                e.onComplete(); // 这里不能先调用  onComplete，如果调用了，那么 onNext就不会被调用
            }
        });


        observable.map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {

                Log.e(TAG, "map数据：" + s);

                // 这里将数据给改变了

                return "你好：廖红杰";
            }
        }).subscribe(observer);


        // 单独这样写，没用，不用调用 map，必须像上面那样链式的调用才有效
        // observable.subscribe(observer); // 订阅

    }
}