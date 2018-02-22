package lhj.dnxy.com.observer;

import android.util.Log;

import java.util.Observable;

/**
 * Created by liaohongjie on 2017/12/14.
 */

public class SimpleObservable extends Observable {

    private int data = 0;
    public int getData(){
        return data;
    }

    public void setData(int value){
        if(this.data != value) {
            this.data = value;
            setChanged(); // 一定要设置这个，否则没有任何响应
            notifyObservers(); // 通知观察者，表示状态发生改变
        }
    }
}
