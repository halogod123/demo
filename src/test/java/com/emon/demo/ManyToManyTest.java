package com.emon.demo;

import com.emon.demo.dao.RoleDao;
import com.emon.demo.dao.UserDao;
import com.emon.demo.entity.Role;
import com.emon.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;


@SpringBootTest
public class ManyToManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 保存一个用户，保存一个角色
     * 多对多放弃维护，被动的一方放弃维护权
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd() {
        User user = new User();
        user.setUsername("小奥");

        Role role = new Role();
        role.setRoleName("学员");
        // 配置用户到角色的关系。可以对中间表的数据维护
        user.getRoles().add(role);
        roleDao.save(role);
        userDao.save(user);
        //
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void testremove() {
        Optional<User> user = userDao.findOne((root, query, cb)->
                cb.equal(root.get("userId"),1L));
        userDao.delete(user.get());
    }
}
