package com.lhj.sample.simple;

import android.util.Log;

import com.lhj.sample.data.Student;
import com.lhj.sample.data.Teacher;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.SyncOnSubscribe;
import rx.schedulers.Schedulers;

/**
 * Created by liaohongjie on 2017/12/14.
 */

public class RxUtils {

    public static void init_1() {
        // 注意：Subscriber与 Subscribe 是有本质的区别的
        // Subscriber 是实现了 Observer 的一个抽象类，Subscriber和Observer两个的使用方式完全一样
        // Subscriber（英文翻译为 订阅者，即 观察者）
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                // 用于在没有 发射事件之前可以做 一些初始化工作
            }

            @Override
            public void onNext(String s) {

                Log.e("TAG", "onNext 线程：" + Thread.currentThread().getName());

                Log.e("TAG", "onNext：" + s);
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable t) {

            }
        };


        //e.onNext("发射数据：Subscriber.....");
        Observable.create(new SyncOnSubscribe<String, String>() {
            @Override
            protected String generateState() {

                Log.e("TAG", "generateState 线程：" + Thread.currentThread().getName());

                return "liaohongjie";
            }

            @Override
            protected String next(String state, Observer<? super String> observer) {

                Log.e("TAG", "next 线程：" + Thread.currentThread().getName());

                Log.e("TAG", "数据：" + state);

                // 让观察者去调用数据
                observer.onNext(state);
                observer.onCompleted();

                return "哈哈哈";
            }
        }).subscribe(subscriber); // 被观察者 订阅 观察者(本来是观察者订阅 被观察者的，但是这样api不好写)

        //.unsubscribe(); // 这里调用了以后，相当于会直接取消 数据接受源，所以一般是在执行完任务后再取消

        // 普通的都是主线程在做
    }

    public static void init_2(){
        Observable<String> just = Observable.just("liao", "hong", "jie");

        // 这两种方式 都行，在rxjava2中 就直接是from了， 而是如fromArray

        // Observable<String> from = Observable.from(new String[]{"liao", "hong", "jie"});

        just.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("TAG", "onNext: " + s);
            }
        });
    }

    public static void init_3(){
        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {

                Log.e("TAG", "发射数据：" + s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
                Log.e("TAG", "出现错误.....");
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.e("TAG", "completed");
            }
        };


        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                subscriber.onNext("廖红杰");
                subscriber.onError(null);
            }
        });

        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        // observable.subscribe(onNextAction);


        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        //   observable.subscribe(onNextAction, onErrorAction);


        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);

        //上面这种写法跟init_1 中是一样的，只是把回调动作全部分拆开了
    }

    /**
     * 线程切换
     *@author liaohongjie
     *created on 2017/12/14 17:44
     */
    public static void init_4() {

        // 定义被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                Log.e("TAG", "call 线程：" + Thread.currentThread().getName());

                // 判断是否取消订阅
                if(!subscriber.isUnsubscribed()) {

                    // subscriber 英文翻译：订阅者（即 观察者）

                    subscriber.onNext("liaohongjie");
                    subscriber.onNext("哈哈");
                    subscriber.onCompleted();
                }
            }
        });


        // 定义 被观察者  更新数据后  观察者所执行的操作（也可以说这个就是观察者）
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onNext(String s) {

                Log.e("TAG", "onNext 线程：" + Thread.currentThread().getName());

                Log.e("TAG", "onNext：" + s);
            }

            @Override
            public void onCompleted() {
                Log.e("TAG", "onCompleted ......");
            }

            @Override
            public void onError(Throwable t) {

            }
        };

        // observable  是被观察者
        // .subscribeOn()  表示 订阅执行的线程(call方法) 类型（主线程，子线程）
        // .observeOn()  表示 观察者 执行操作的 线程类型(一般是主线程)
        // .unsubscribeOn()  表示 取消订阅操作的线程
        // .subscribe()  表示 进行订阅，让观察者 订阅 被观察者（注意api是反过来调用的，这是为api方便）
        observable
                .subscribeOn(Schedulers.io()) //事件产生所使用的线程（事件生产线程，即监听者执行任务的线程）
                .observeOn(AndroidSchedulers.mainThread()) // 事件消费线程(即被监听者执行回调所产生的线程)
                .unsubscribeOn(Schedulers.io()) //资源释放在io线程
                .subscribe(subscriber);
    }

    /**
     *map 一对一的变换
     *@author liaohongjie
     *created on 2017/12/14 17:43
     */
    public static void init_5() {

        Observable<Student> observable = Observable.just(new Student("廖红杰", 24));

        // map 操作符，将一个类型的数据 变换 为另一个类型的数据
        // map 是 一对一 的变换
        observable.map(new Func1<Student, Teacher>() {
            @Override
            public Teacher call(Student student) {

                Log.e("TAG", "map 线程：" + Thread.currentThread().getName());

                Log.e("TAG", "map数据: " + student.name);

                // 直接创建对象是一种方式
                return new Teacher(student.name, student.age); //将学生变为老师
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                /*.subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                        Log.e("TAG", "Action1 线程：" + Thread.currentThread().getName());

                        Log.e("TAG", "Action1数据：" + s);
                    }
                });*/
                // 跟上面的效果一样，这中方式适合 网络结果返回
                // 上面的那种只适合具体的单个操作的逻辑业务
                .subscribe(new Subscriber<Teacher>() {
                    @Override
                    public void onCompleted() {

                        Log.e("TAG", "Action1 线程：" + Thread.currentThread().getName());

                        Log.e("TAG", "Subscriber --> onCompleted..... ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Teacher teacher) {
                        Log.e("TAG", "onNext数据：" + teacher.name);
                    }
                });
    }

    /**
     *一对多的变换
     *@author liaohongjie
     *created on 2017/12/14 17:43
     */
    public static void intt_6() {

        Student[] student = new Student[]{new Student("廖红杰", 24), new Student("万慧", 25), new Student("乐泽君", 11)};

//        flatMap 一对多

        Observable
                .from(student)
                .flatMap(new Func1<Student, Observable<Student>>() {
                    @Override
                    public Observable<Student> call(Student student) {
                        // 只不过将原来的数据重新封装了然后继续往下, 如果
                        return Observable.just(student);// 这样不是能体现的出一对多，其实看起来是一对一
                        // return Observable.from(student); //这里如果再次使用 from 才能体现得出 一对多
                    }
                })
                .subscribe(new Action1<Student>() {
                    @Override
                    public void call(Student student) {
                        Log.e("TAG", student.name + "  :  " + student.age);
                    }
                });


        // flatMap和map操作符很相像，flatMap发送的是合并后的Observables，map操作符发送的是应用函数后返回的结果集

        Observable<Student> observable = Observable.just(new Student("廖红杰", 24));

        // 如果使用map再次进行封装就不行，map这个一对一 ，是返回什么，下面的 Action中的泛型就是什么
        // 所以如果想要重新封装对象 需要 使用 flatMap
        observable.map(new Func1<Student, Observable<Teacher>>() {
            @Override
            public Observable<Teacher> call(Student student) {
                Log.e("TAG", "map 线程：" + Thread.currentThread().getName());
                Log.e("TAG", "map数据: " + student.name);
                return Observable.just(new Teacher(student.name, 24)); //将学生变为老师
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Action1<Observable<Teacher>>() {
                    @Override
                    public void call(Observable<Teacher> teacherObservable) {
                        // 所以这里不好处理，即使处理了也有点显得多余
                    }
                });
    }

    /**
     *防抖动
     *@author liaohongjie
     *created on 2017/12/14 17:43
     */
    public static void init_7() {

        // 在发送短信操作的时候，不小心点击了两次，此时就可以使用这个

        Observable.from(new String[]{"liao", "hong", "jie"})
                // 意思是：在1秒以内，在第一个事件过后的剩余事件将全部丢弃，所以下面的打印结果只有一个
                // TimeUnit.MILLISECONDS 是表示第一个数的单位是以 秒 还是 毫秒计数
                .throttleFirst(1000, TimeUnit.MILLISECONDS, Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {

                        Log.e("TAG", "Action数据：" + s);
                    }
                });


    }

    public static void init_8() {
        // 一种空的写法

        Observable.empty()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<Object>() {
                    @Override
                    public void call(Object o) {

                        //  empty 就不会调用 doOnNext 中的call，会直接调用 doOnCompleted方法

                        Log.e("TAG", " doOnNext... : " + o.toString());
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        Log.e("TAG", " doOnCompleted... : ");
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("TAG", " doOnError... : ");
                    }
                })
                .subscribe();

        Observable.just("廖红杰")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnNext(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("TAG", "doOnNext 线程：" + Thread.currentThread().getName());
                        Log.e("TAG", "doOnNext : " + s);
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        Log.e("TAG", "doOnCompleted....");
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e("TAG", "doOnError....");
                    }
                }).subscribe();

    }
}
