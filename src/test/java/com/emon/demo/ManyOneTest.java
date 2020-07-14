package com.emon.demo;

import com.emon.demo.dao.CustomerDao;
import com.emon.demo.dao.LinkManDao;
import com.emon.demo.entity.Customer;
import com.emon.demo.entity.LinkMan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;


@SpringBootTest
public class ManyOneTest {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testAdd(){
        Customer customer = new Customer();
        customer.setCustName("小舞");
        customer.setCustLevel("benke");
        customer.setCustIndustry("史莱克学院");
        customer.setCustAddress("天斗城");
        customer.setCustPhone("114");
        customer.setCustSource("星斗大森林");
        /// customerDao.save(customer);
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小刚");
//
//        List<Customer> list = new ArrayList<>();
//        list.add(customer);
        linkMan.setCustomer(customer);
        //customer.getLinkMans().add(linkMan);

        customerDao.save(customer);

        linkManDao.save(linkMan);

    }
    @Test
    @Transactional
    @Rollback(value = false)
    public void testDel(){
        Customer customer = new Customer();
        customer.setCustId(1L);
        customer = customerDao.findOne(Example.of(customer)).get();
        customerDao.delete(customer);

    }
    @Test
    void findByCustNameLikeAndCustIndustry() {
        Customer c = customerDao.findByCustName("小舞");
        System.out.println("c = " + c);
    }
}
