package com.emon.demo.dao;

import com.emon.demo.entity.Role;
import com.emon.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleDao extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {

}
