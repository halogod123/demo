package com.emon.demo.dao;

import com.emon.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpecificationTest extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

}
