package com.lhj.sample;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;

/**
 * Created by LiaoHongjie on 2017/7/3.
 */

public interface CacheProviders<T> {
    /**
     * 更新缓存中的某条数据
     */
    void updateSingleData(T newData);
    /**
     * 插入或者替换缓存中的某条数据
     */
    long insertOrReplace(T newData);


    Observable<UserBean> getUserBean();

    // 设置缓存失效时间是10分钟
    @LifeCache(duration = 10, timeUnit = TimeUnit.MINUTES)
    void insertUserBean(UserBean userBean);
}
