package com.emon.demo;

import com.emon.demo.dao.CustomerDao;
import com.emon.demo.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpqlTests {

	public static void main(String[] args) {
		Integer[] array={10,20,40,1};
		Optional<Integer> i = Arrays.stream(array)
								.reduce((a,b) -> a+b);
		System.out.println("i.get() = " + i.get());
	}

	@Autowired
	private CustomerDao customerDao;
    /**
	 */
	@Test
	public void testGetOne(){
		List<Customer> isExit = customerDao.findJpql("emon");
		System.out.println("isExit = " + isExit);
	}
	/**
	 */
	@Test
	public void testNameAndId(){
		List<Customer> isExit = customerDao.findNameAndId("emon",2L);
		System.out.println("isExit = " + isExit);
	}

	/**
	 * 更新方法需要添加
	 * @Transactional
	 * @Rollback(value=false)
	 */
	@Test
	@Transactional
	@Rollback(value=false)
	public void testupdate(){

		customerDao.updateCustomer("唐三",3L);
//		System.out.println("isExit = " + isExit);
	}

	/**
	 */
	@Test
	public void testSqlSelect(){

		customerDao.findBysql().forEach(s -> System.out.println("s = " + Arrays.toString(s)));
//		System.out.println("isExit = " + isExit);findBysqlName
	}


	/**
	 */
	@Test
	public void findBysqlName(){
		customerDao.findBysqlName("e").forEach(s -> System.out.println("s = " + Arrays.toString(s)));
//		System.out.println("isExit = " + isExit);findBysqlName
	}
	/**
	 * 方法名的约定
	 * findBy :查询
	 * 对象中的属性名（首字母大写）；查询条件
	 * CustName
	 * findByCustName  -- 根据客户名称查询
	 * 在springdataJpa的运行阶段
	 * 会根据方法名进行解析 findBy from xxx(实体类)
	 * 属性名称  where custName =
	 */
	@Test
	void findByCustName() {

		System.out.println("customerDao = " + customerDao.findByCustName("唐三"));
	}

	@Test
	void findByCustNameLink() {

		System.out.println("customerDao = " + customerDao.findByCustNameLike("%三%"));
	}

	@Test
	void findByCustNameLikeAndCustIndustry() {
		Customer c = customerDao.findByCustNameLikeAndCustIndustry("唐%","诺丁城学院");
		System.out.println("c = " + c);
	}
	/**
	 * Specification:查询条件
	 * 自定义我们自己的Specification实现类
	 *	实现
	 * root:查询的根对象
	 *
	 */
	
}
