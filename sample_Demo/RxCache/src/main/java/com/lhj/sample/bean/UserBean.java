package com.lhj.sample.bean;

import java.io.Serializable;

/**
 * Created by LiaoHongjie on 2017/7/3.
 */

public class UserBean implements Serializable {

    public UserBean(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String name;
    public int age;
}
