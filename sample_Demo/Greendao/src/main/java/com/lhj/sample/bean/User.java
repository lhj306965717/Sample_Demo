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
    @Id(autoincrement = true)
    private Long id; // 这个是主键ID(自增id)   // 注意：这个Id类型一定要是 Long 类型，不能是long，否则坑爹
    private String name;
    private int age;
    @Generated(hash = 1309193360)
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
