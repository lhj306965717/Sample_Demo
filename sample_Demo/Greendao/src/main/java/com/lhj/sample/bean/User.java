package com.lhj.sample.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by LiaoHongjie on 2017/7/4.
 */

/*
 greenDao多使用注解，如果你要将某一实体存储到数据库中，需要先对实体进行编写。
 @Entity表明该类是持久化的类【持久化含义，存入数据库文件中，作本地化处理】
 @Id选择一个long或Long类型的属性作为该实体所对应数据库中数据表的主键【类型要是long】
 @Generated写在构造方法前
*/

@Entity
public class User {
    @Id
    private long id; // 这个是主键ID
    private String name;

    @Generated(hash = 586692638) // 这个数据一定要是long类型的，否则会生成失败
    public User() {
    }

    @Generated(hash = 1144922831)
    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
