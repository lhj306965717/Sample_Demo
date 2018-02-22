package com.lhj.retrofit2.http;

import okhttp3.MediaType;

/**
 * Created by liaohongjie on 2017/9/15.
 */

public interface HttpURL {

    String HEADER = "Content-type:application/json; charset=UTF-8";

    MediaType JSON = MediaType.parse("application/json; charset=UTF-8");

    String FORM = "Content-Type: application/x-www-form-urlencoded;charset=UTF-8";

    String URL = "http://119.23.42.170:8080";

    String LOGIN = "/mobileapi/login";
}
