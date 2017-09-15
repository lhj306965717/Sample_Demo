package com.lhj.retrofit2.request;

import com.lhj.retrofit2.http.HttpURL;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Path;

/**
 * Created by liaohongjie on 2017/9/15.
 */

public interface Login {

    @HEAD(HttpURL.HEADER)
    @GET(HttpURL.LOGIN + "?loginname={userName}&pwd={pwd}&devicecode={devicecode}")
    Call<RequestBody> login(@Path("userName") String account, @Path("pwd") String pwd, @Path("devicecode") String devicecode);

}
