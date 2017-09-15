package com.lhj.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lhj.retrofit2.base.BaseApplication;
import com.lhj.retrofit2.request.Login;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onResume() {
        super.onResume();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Retrofit retrofit = BaseApplication.getRetrofit();
                Login login = retrofit.create(Login.class);

                Call<RequestBody> requestBodyCall = login.login("17360037758", "123456", "123456");

                requestBodyCall.enqueue(new Callback<RequestBody>() {
                    @Override
                    public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                        String message = response.message();
                        Log.e("TAG", "消息："+message);
                    }

                    @Override
                    public void onFailure(Call<RequestBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });

            }
        }).start();
    }
}
