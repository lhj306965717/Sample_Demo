package com.lhj.sample;

import android.util.Base64;
import android.util.Log;

import com.lhj.sample.bean.UserBean;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import rx.Observable;


/**
 * Created by LiaoHongjie on 2017/7/3.
 */
// 不能直接用接口，因为不是配合 retrofit 使用，
public class CacheProviders {
    String key = "liaohongjie";
    private final DiskCache mDiskCache;

    public CacheProviders(){
        mDiskCache = new DiskCache(BaseApplication.getContext(), "liaohongjie");
    }

    public Observable<UserBean> getUserBean(){

        return null;
    }

    // 设置缓存失效时间是10分钟，如果不是配合 retrofit 使用就不再使用 注解@LifeCache
   // @LifeCache(duration = 10, timeUnit = TimeUnit.MINUTES)
    public void insertUserBean(UserBean userBean){
        String str = object2Base64Str(userBean);
        Log.e("TAG", "数据："+str);
        mDiskCache.wirteData(key, str);
    }

    public static <T> T base64Str2Object(String productBase64) {
        T device = null;
        if (productBase64 == null) {
            return null;
        }
        // 读取字节
        byte[] base64 = Base64.decode(productBase64.getBytes(), Base64.DEFAULT);

        // 封装到字节流
        ByteArrayInputStream bais = new ByteArrayInputStream(base64);
        try {
            // 再次封装
            ObjectInputStream bis = new ObjectInputStream(bais);
            // 读取对象
            device = (T) bis.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return device;
    }

    public static <T> String object2Base64Str(T object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {   //Device为自定义类
            // 创建对象输出流，并封装字节流
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            // 将对象写入字节流
            oos.writeObject(object);
            // 将字节流编码成base64的字符串
            return new String(Base64.encode(baos
                    .toByteArray(), Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
