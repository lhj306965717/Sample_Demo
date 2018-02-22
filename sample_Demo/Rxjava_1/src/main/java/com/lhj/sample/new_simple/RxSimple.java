package com.lhj.sample.new_simple;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liaohongjie on 2017/12/14.
 */

public class RxSimple {

    /**
     * 第一种使用方式
     */
    public static void init_from_1() {

        Integer[] items = new Integer[]{1, 2, 3, 4, 5};

        // From 操作：将其他种类的对象和数据类型转换为Observable
        Observable.from(items)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError...");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("TAG", "数据：" + integer);
                    }
                });
    }

    /**
     * 第二种使用方式
     */
    public static void init_from_2() {

        Integer[] items = new Integer[]{1, 2, 3, 4, 5};

        // 这其实就是创建 一个数据迭代器的 Observable

        // From 操作：将其他种类的对象和数据类型转换为Observable
        // 当你使用Observable时，如果你要处理的数据都可以转换成展现为Observables,
        // 而不是需要混合使用Observable和其他类型的数据
        Observable<Integer> from = Observable.from(items);

        // 注意：这里的Action就是 观察者
        from.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer inr) {
                Log.e("TAG", "数据：" + inr);
            }
        });

    }

    /**
     * 定时发送数据
     */
    public static void init_interval() {

        // 注意：第一个参数表示：发射第一个数据的延时时常
        // 第二个参数 表示 发射一个数据后的其他数据的相隔时常
        final Observable<Long> observable = Observable.interval(8, 1, TimeUnit.SECONDS);// 每隔一秒发送一个数据
        observable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.e("TAG", "map 线程：" + Thread.currentThread().getName());

                Log.e("TAG", "Long: " + aLong);
            }
        });
    }

    /**
     * just 是将多个数据 合并 到一起 输出
     */
    public static void init_just() {

        Integer[] items1 = new Integer[]{1, 2, 3, 4, 5};
        Integer[] items2 = new Integer[]{5, 6, 7, 8, 9};
        Integer[] items3 = new Integer[]{10, 11, 12, 13, 14};

        Observable<Integer[]> observable = Observable.just(items1, items2, items3);

        observable.subscribe(new Action1<Integer[]>() {
            @Override
            public void call(Integer[] integers) {
                for (int i : integers) {
                    Log.e("TAG", "数组：" + i);
                }
            }
        });
    }

    /**
     * 输出从哪里到哪里
     */
    public static void init_range() {
        /**
         * 输出 1~10，且并没有延时
         * */
        Observable<Integer> observable = Observable.range(1, 10);
        observable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.e("TAG", "range: " + integer);
            }
        });
    }

    /**
     * filter  进行过滤操作
     */
    public static void init_just_2() {

        // 过滤：被观察者 向 观察者 发送消息的时候先过滤一遍再发给观察者

        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9);
        /** 注意：
         *  这里使用的是 filter
         * */
        observable
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {

                        //Log.e("TAG", "判断："+(integer < 6));

                        // 只有这里返回true的结果，数据才会发射
                        return integer < 6;
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        Log.e("TAG", "onCompleted...");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError...");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("TAG", "filter: " + integer);
                    }
                });
    }
}
