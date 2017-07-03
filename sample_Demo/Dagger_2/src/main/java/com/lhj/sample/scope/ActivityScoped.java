package com.lhj.sample.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by LiaoHongjie on 2017/6/30.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScoped { //一个注解类
}
