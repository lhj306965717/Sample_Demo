package lhj.dnxy.com.observer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by liaohongjie on 2017/12/14.
 */

public class SimpleObserver implements Observer {

    private final Context mContext;

    public SimpleObserver(Context context, Observable observable){
        observable.addObserver(this);
        this.mContext = context;
    }

    @Override
    public void update(Observable o, Object arg) {
        Toast.makeText(mContext, "数据："+ ((SimpleObservable)o).getData(), Toast.LENGTH_SHORT).show();
    }
}
