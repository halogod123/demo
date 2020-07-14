package com.emon.demo;

import com.emon.demo.dao.CustomerDao;
import com.emon.demo.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaRepositoryTests {

	@Autowired
	private CustomerDao customerDao;

	@Test
	public void contextLoads() {
//		Example<Customer> example = new Example<Customer>() {
//			@Override
//			public Customer getProbe() {
//				Customer customer = new Customer();
//				customer.setCust_id(1L);
//				return customer;
//			}
//
//			@Override
//			public ExampleMatcher getMatcher() {
//				return ExampleMatcher.matching();
//			}
//		};
		Customer customer = new Customer();
		customer.setCustId(1L);
		Optional<Customer> cc = customerDao.findOne(Example.of(customer));
        System.out.println("c = " + cc.get());
	}
	@Test
	public void saveTest() {
		Customer customer = new Customer();
		customer.setCustName("emon");
		customer.setCustLevel("vip");
		customer.setCustIndustry("IT教育");
		customerDao.save(customer);
	}
	@Test
	public void saveTest2() {
		Customer customer = new Customer();
//		customer.setCust_id(3L);
		customer.setCustName("emon");
		customer.setCustLevel("vip");
		customer.setCustIndustry("蓝银草武魂修炼");
		customerDao.save(customer);
	}
	// 主键已经存时，更新
	@Test
	public void saveTest3() {
		Customer customer = new Customer();
		customer.setCustId(4L);
		customer.setCustName("emon");
		customerDao.save(customer);
	}
	// 主键已经存时，更新
	@Test
	public void delTest() {
		Customer customer = new Customer();
		customer.setCustId(4L);
		customerDao.delete(customer);


	}
	// 主键已经存时，更新
	@Test
	public void findAllTest() {
		List<Customer> list = customerDao.findAll();

		list.forEach(s -> System.out.println("s = " + s));
	}
	/**
	 * 测试统计查询，查询客户的总数量
	 */
	@Test
	public void testCount(){
		long count = customerDao.count();
		System.out.println("count = " + count);
	}

	/**
	 * 查询：判断id为4的客户是否存在
	 * 		如果为空
	 */
	@Test
	public void testExit(){
		Customer customer = new Customer();
		customer.setCustId(4L);
		boolean isExit = customerDao.exists(Example.of(customer));
		System.out.println("isExit = " + isExit);
	}

	/**
	 * 根据id从数据库查询
	 * findOne: em.findOne  立即加载
	 * getOne： em.getReference  延迟加载
	 * @Transactional
	 */
	@Test
	@Transactional
	public void testGetOne(){
//		Customer customer = new Customer();
//		customer.setCust_id(4L);
		Customer isExit = customerDao.getOne(1L);
		System.out.println("isExit = " + isExit);
	}

}
