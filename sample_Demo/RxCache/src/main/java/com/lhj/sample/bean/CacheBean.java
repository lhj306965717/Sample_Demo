package com.lhj.sample.bean;

/**
 * Created by LiaoHongjie on 2017/7/4.
 */

public class CacheBean {

    // 对象缓存过期时间
    private static final int EXPIRE_LIMIT = 60 * 60 * 1000;// 一小时
    // 对象创建的时间
    private long mCreatTime;

    public CacheBean() {
        mCreatTime = System.currentTimeMillis();
    }

    /**
     * 判断是否过期,有必要可以重写该方法
     */
    public boolean isExpire() {
        return (System.currentTimeMillis() - mCreatTime) / 1000 > EXPIRE_LIMIT;
    }

    @Override
    public String toString() {
        return "CacheBean{" +
                "mCreatTime=" + mCreatTime +
                '}';
    }

}
