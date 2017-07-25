package com.lhj.sample;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import io.victoralbertos.jolyglot.JolyglotGenerics;
import io.victoralbertos.jolyglot.Types;


/**
 * Created by LiaoHongjie on 2017/7/3.
 */

public class GsonSpeaker implements JolyglotGenerics {

    private final Gson mGson;

    public GsonSpeaker(){
        this.mGson = new Gson();
    }

    @Override
    public String toJson(Object src, Type typeOfSrc) {
        return mGson.toJson(src, typeOfSrc);
    }

    @Override
    public <T> T fromJson(String json, Type type) throws RuntimeException {
        return mGson.fromJson(json, type);
    }

    @Override
    public <T> T fromJson(File file, Type typeOfT) throws RuntimeException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            T object =  mGson.fromJson(reader, typeOfT);
            reader.close();
            return object;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException i) {}
            }
        }
    }

    @Override
    public GenericArrayType arrayOf(Type componentType) {
        return Types.arrayOf(componentType);
    }

    @Override
    public ParameterizedType newParameterizedType(Type rawType, Type... typeArguments) {
        return Types.newParameterizedType(rawType, typeArguments);
    }

    @Override
    public String toJson(Object src) {
        return mGson.toJson(src);
    }

    @Override
    public <T> T fromJson(String json, Class<T> classOfT) throws RuntimeException {
        return mGson.fromJson(json, classOfT);
    }

    @Override
    public <T> T fromJson(File file, Class<T> classOfT) throws RuntimeException {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            T object =  mGson.fromJson(reader, classOfT);
            reader.close();
            return object;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException i) {}
            }
        }
    }
}
