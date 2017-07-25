package com.lhj.sample;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import com.jakewharton.disklrucache.DiskLruCache;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LiaoHongjie on 2017/7/4.
 */

//  这个类最好做成单例，或者用 Dagger2
public class DiskCache {

    private static final long CACHE_SZIE = 1024 * 1024 * 50;
    private DiskLruCache mDiskLruCache;

    public DiskCache(Context context, String fileName) {
        try {
            File file = getDiskCacheDir(context, fileName);

            Log.e("TAG", file.getAbsolutePath());

            if (!file.exists()) {
                file.mkdirs();
            }

            // 注意：如果file存在，且有文件的就直接读取

            mDiskLruCache = DiskLruCache.open(file, getAppVersion(context), 1, CACHE_SZIE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wirteData(String key, String value) {
        DiskLruCache.Editor editor = null;
        try {
            key = hashKeyForDisk(key); // 需要进行编码
            editor = mDiskLruCache.edit(key);
            if (editor != null) {
                OutputStream outputStream = editor.newOutputStream(0);
                OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
                writer.write(value, 0, value.length());
                writer.flush();

                editor.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (editor != null)
                try {
                    editor.abort(); //放弃
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }
    }

    public String readData(String key) {

        key = hashKeyForDisk(key); // 需要进行编码
        InputStreamReader reader = null;
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(key);
            InputStream inputStream = snapshot.getInputStream(0);
            reader = new InputStreamReader(inputStream);

            CharArrayWriter writer = new CharArrayWriter();

            char[] ch = new char[1024 * 10];
            int len = 0;
            while ((len = reader.read(ch)) != -1) {
                writer.write(ch, 0, len);
                writer.flush();
            }
            String data = writer.toString();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void removeData(String key) {
        try {
            key = hashKeyForDisk(key); // 需要进行编码
            mDiskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回缓存数据的大小
     *
     * @return
     */
    public long getCacheSzie() {
        return mDiskLruCache.size();
    }

    /**
     * 同步缓存操作
     */
    public void flushCache() {
        try {
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将DiskLruCache关闭掉，是和open()方法对应的一个方法
     * 关闭掉了之后就不能再调用DiskLruCache中任何操作缓存数据的方法
     */
    public void CloseCache() {
        try {
            mDiskLruCache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有缓存
     */
    public void deleteCache() {
        try {
            mDiskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }


    /**
     * 生产 唯一的 ULR key,通过 将URL地址的字符串进行MD5
     *
     * @param key
     * @return
     */
    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
