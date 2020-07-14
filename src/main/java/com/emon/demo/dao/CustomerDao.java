package com.emon.demo.dao;

import com.emon.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 符合SpringDataJpa的dao层接口规范
 *  JpaRepository<操作的实体类类型，实体类中主键属性的类型>
 *      *封装了基本的crud操作
 *  JpaSpecificationExecutor<操作实体类类型>、
 *      *封装了复杂的查询（分页等）
 */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    /**
     * 使用客户名称查询客户
     * 使用jpql的形式查询
     * jpql: from Customer where cust_name = ?
     * 配置jpql语句，使用@Query注解
     */

    @Query(value="from Customer where cust_name = :cust_name")
    List<Customer> findJpql(@Param("cust_name") String cust_name);

    /**
     * 根据：客户名称和客户id查询客户
     */
    @Query(value="from Customer where cust_name = :cust_name and cust_id = :id")
    List<Customer> findNameAndId(@Param("cust_name") String cust_name,@Param("id") Long cust_id);

    /**
     * 根据：更新客户名
     * @Query；代表进行查询
     * @Modifying 代表的是当前执行的是一个更新操作
     */

    @Query(value=" update Customer set cust_name= :cust_name where cust_id = :cust_id")
    @Modifying
    void updateCustomer(@Param("cust_name") String cust_name,@Param("cust_id") Long cust_id);

    /**
    * 使用sql的形式查询
     * sql:select * from cst_customer
     * 使用原生sql查询的结构都是 List<Object[]>    </>
     */
    @Query(value=" select * from cst_customer",nativeQuery = true)
    List<Object[]> findBysql();

    @Query(value=" select * from cst_customer where cust_name like ?1%",nativeQuery = true)
    List<Object[]> findBysqlName(String name);

    /**
     * 方法名的约定
     * findBy :查询、
     * 对象中的属性名（首字母大写）；查询条件
     * CustName
     * findByCustName  -- 根据客户名称查询
     * 在springdataJpa的运行阶段
     * 会根据方法名进行解析 findBy from xxx(实体类)
     *                          属性名称  where custName =
     *  1.findBy + 属性名称（根据属性名查询）
     *  2.findBy + 属性名称 + 查询方式（like | isnull） 如： findByCustNameLike
     *  3.多条件查询
     *    findBy + 属性名 + “查询方式” + "多条件的连接符（and|or）" + 属性名 + “查询方式”
     */
    Customer findByCustName(String custName);

    /**
     * 模糊查询
     * @param custName
     * @return
     */
    List<Customer> findByCustNameLike(String custName);

    /**
     * 使用客户名称模糊匹配和客户所属行业精准匹配的查询
     */
    Customer findByCustNameLikeAndCustIndustry(String custName,String custIndustry);
}
