package com.lhj.eventbus;

/**
 * Created by Administrator on 2017/9/5.
 */

public class Message {

    private final String mTag;
    private String message;

    public Message(String message, String tag){
        this.message = message;
        this.mTag = tag;
    }

    public String getmTag() {
        return mTag;
    }

    public String getMessage() {
        return message;
    }
}
