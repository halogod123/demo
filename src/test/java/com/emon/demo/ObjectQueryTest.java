package com.emon.demo;

import com.emon.demo.dao.CustomerDao;
import com.emon.demo.dao.LinkManDao;
import com.emon.demo.entity.Customer;
import com.emon.demo.entity.LinkMan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * 对象导航查询
 */
@SpringBootTest
public class ObjectQueryTest {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 对象导航查询(一对多查询，)
     *          默认使用延迟加载的方式，调用get方法不会立即加载，而是在使用对象的时候才会使用
     *  修改配置，将延迟加载改为立即加载
     *      fetch,需要配置到多表映射关系的注解上面
     *
     *     #####重点  一对多的查询，在调用对象导航查询 查询多的一方数据时默认采用懒加载
     *              想使用立即加载 则 fetch = FetchType.EAGER,需要配置到多表映射关系的注解上面
     *
     */
    @Test
    @Transactional  // no Session 异常 采用事务的方式解决
    public void testQuery(){
        Customer cc = new Customer();
        cc.setCustId(1L);
        Customer customer = customerDao.findOne(Example.of(cc)).get();
        // 对象导航查询 通过get方法直接查询出linkMans
        Set<LinkMan> linmans = customer.getLinkMans();
        System.out.println("customer = " + customer);
        linmans.forEach(s -> System.out.println("s = " + s));
    }

    /**
     * 对象导航查询（一对多查询）
     *          查询一的一方的时候默认使用立即加载的方式，
     *      fetch = FetchType.LAZY,需要配置到多表映射关系的注解上面
     *
     */
    @Test
    @Transactional  // no Session 异常 采用事务的方式解决
    public void testQuery2(){
        LinkMan cc = new LinkMan();
        cc.setLkmId(1L);
        LinkMan linkMan = linkManDao.findOne(Example.of(cc)).get();
        // 对象导航查询 通过get方法直接查询出linkMans
        Customer customer = linkMan.getCustomer();
        System.out.println("customer = " + customer);
        // linmans.forEach(s -> System.out.println("s = " + s));
    }

    /**
     * Specification的多表查询
     */
    @Test
    public void testFind() {
        Specification<LinkMan> spec = new Specification<LinkMan>() {
            public Predicate toPredicate(Root<LinkMan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //Join代表链接查询，通过root对象获取
                //创建的过程中，第一个参数为关联对象的属性名称，第二个参数为连接查询的方式（left，inner，right）
                //JoinType.LEFT : 左外连接,JoinType.INNER：内连接,JoinType.RIGHT：右外连接
                Join<LinkMan, Customer> join = root.join("customer", JoinType.INNER);
                return cb.like(join.get("custName").as(String.class),"唐三");
            }
        };
        List<LinkMan> list = linkManDao.findAll(spec);
        for (LinkMan linkMan : list) {
            System.out.println(linkMan);
        }
    }

}
