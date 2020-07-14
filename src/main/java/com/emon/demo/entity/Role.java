package com.emon.demo.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long Id;

    @Column(name="role_name")
    private String roleName;


    /**
     * 配置用户到角色的多对多关系
     * 配置多对多的映射关系
     *  1.声明表关系
     *      targetEntity 对方的实体类字节码
     *  2.配置中间表（包含两个外键）
     * @return
     */
//    @ManyToMany(targetEntity = User.class)
//    @JoinTable(name="sys_user_role",
//            // inverseJoinColumns,对方对象在中间表的外键
//            inverseJoinColumns = {@JoinColumn(name="sys_user_id",referencedColumnName = "user_id")},
//            // 当前对象在中间表中的外键
//            joinColumns = {@JoinColumn(name="sys_role_id",referencedColumnName = "role_id")}
//    )

    // private Set<User> users  = new HashSet<>();

//    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
