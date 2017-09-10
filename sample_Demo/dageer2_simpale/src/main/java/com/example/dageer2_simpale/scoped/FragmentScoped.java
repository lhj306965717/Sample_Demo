package com.example.dageer2_simpale.scoped;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by liaohongjie on 2017/9/10.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentScoped {
}
