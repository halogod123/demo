package com.emon.demo.entity;

import sun.awt.OSInfo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sys_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    @Column(name="user_name")
    private String username;
    @Column(name="user_age")
    private String age;

    /**
     * 配置用户到角色的多对多关系
     * 配置多对多的映射关系
     *  1.声明表关系
     *      targetEntity 对方的实体类字节码
     *  2.配置中间表（包含两个外键）
     *
     *  cascade = CascadeType.ALL 配置级联删除
     * @return
     */
    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinTable(name="sys_user_role",
            // 当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name="sys_user_id",referencedColumnName = "user_id")},
            // inverseJoinColumns,对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name="sys_role_id",referencedColumnName = "role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
