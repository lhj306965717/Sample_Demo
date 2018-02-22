package com.lhj.sample.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by LiaoHongjie on 2017/7/4.
 */

/*
 greenDao多使用注解，如果你要将某一实体存储到数据库中，需要先对实体进行编写。
 @Entity表明该类是持久化的类【持久化含义，存入数据库文件中，作本地化处理】
 @Id选择一个long或Long类型的属性作为该实体所对应数据库中数据表的主键【类型要是long】
 @Generated写在构造方法前
*/

@Entity(/*schema = ??? , 有很多属性可以配置*/) //告诉GreenDao 该Bean类需要持久化。只有使用@Entity注释的Bean类才能被dao类操作;
public class User {

    @Id(autoincrement = false)
    private Long id; // 这个是主键ID(自增id)   // 注意：这个Id类型一定要是 Long 类型，不能是long，否则坑爹
    // 如果不想使用 Long型作为主键，你可以使用一个唯一索引(使用@Index(unique = true)注释使普通属性改变成唯一索引属性)属性作为关键属性。
    // 使用@Index 可以将一个属性变为数据库索引；其有俩个参数
//    name :不使用默认名称，自定义索引名称
//    unique : 给索引增加一个唯一约束，迫使该值唯一

    private String name;
    private int age;


    // 提示开发者该属性不能被修改；并且实体类的方法，属性，构造器一旦被@Generated注释就不能被再次修改，否则或报错
    @Generated(hash = 1309193360)
    public User(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Transient // 表示这个字段不存入数据库
    private String sex;

    @NotNull // 确保这个字段值不为空
    @Property(nameInDb = "stature") // 将数据库中的字段重新命名成 这个  字符串
    private int height;

    @Unique // 将属性变成唯一约束属性;也就是说在数据库中该值必须唯一
    private int idCard; // 身份证不能重复


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
