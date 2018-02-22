package lhj.dnxy.com.observer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void bt_observer(View view){

        SimpleObservable observable = new SimpleObservable();
        Observer observer = new SimpleObserver(getApplicationContext(), observable);

        observable.setData(1);
        observable.setData(2);
        observable.setData(2);
        observable.setData(3);
    }
}
