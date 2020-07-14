package com.emon.demo;

import com.emon.demo.dao.SpecificationTest;
import com.emon.demo.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

@SpringBootTest
public class SpecificationTestDemo {

    @Autowired
    private SpecificationTest specificationTest;

    /**
     * 根据条件查询单个对象
     * 自定义查询条件
     *  1.实现Specification接口（提供泛型，查询的条件对象类型）
     *  2.实现toPredicate方法（构造查询条件）
     *  3.借助方法参数中的两个参数（
     *      root:获取需要查询的对象属性
     *      CriteriaBuilder:构造查询条件，内部封装了很多的查询条件（模糊匹配，精准匹配）
     *  ）
     * 根据客户名称查询
     */
    @Test
    public void test1(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 1.获取比较属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件
                Predicate predicate = criteriaBuilder.equal(custName,"唐三"); // 进行精准匹配（属性，属性的值）
                return predicate;
            }
        };
        Customer customer = specificationTest.findOne(spec).get();
        System.out.println("customer = " + customer);
    }
    // jdk8改写
    @Test
    public void test1_jdk8(){
        Customer customer = specificationTest.findOne((root,criteriaQuery,criteriaBuilder)
                ->criteriaBuilder.equal(root.get("custName"),"唐三"))
                .get();
        System.out.println("customer = " + customer);
    }

    /**
     * 多条件查询
     *   案例：根据客户名和客户所属行业查询
     *
     */
    @Test
    public void testSpec1(){
        /**
         * root：获取属性
         *  客户名
         *  所属行业
         * criteriaBuilder：构造查询
         *  1.构造客户名的精准查询
         *  2.构造所属行业的精准匹配查询
         *  3.将以上两个查询联合起来
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                // 构造查询
                // 1.构造客户名的精准匹配查询
                Predicate p1 = criteriaBuilder.equal(custName,"唐三");
                Predicate p2 = criteriaBuilder.equal(custIndustry,"诺丁城学院");
                // 将多个查询条件组合到一起，组合（满足条件1并且满足条件二，）
                Predicate predicate =  criteriaBuilder.and(p1,p2);  // 以于的方式拼接多个查询条件
                // criteriaBuilder.or(p1,p2); // 以或的方式拼接多个查询条件
                return predicate;
            }
        };
        Customer customer = specificationTest.findOne(spec).get();
        System.out.println("customer = " + customer);
    }
    // jdk8改写 testSpec1
    @Test
    public void testSpec1_jdk8(){
        Customer customer = specificationTest.findOne((root,cquery,cb)
                -> cb.and(cb.equal(root.get("custName"),"唐三")
                 ,cb.equal(root.get("custIndustry"),"诺丁城学院")
        )).get();
        System.out.println("customer = " + customer);
    }
    /**
     *  模糊查询
     *   equal:直接到path对象（属性），然后比较即可
     *   gt,lt,ge,le,like:得到path对象，根据path 指定path 指定比较的参数类型，再去进行比较
     *   指定参数类型：path.as(类型的字节码对象)
     */
    @Test
    public void testSpec2(){
        /**
         *构造查询条件
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String> custName = root.get("custName");
                Path<String> custIndustry = root.get("custIndustry");
                // 构造查询
                // 1.构造客户名的精准匹配查询
                Predicate p1 = criteriaBuilder.like(custName,"_三");
                Predicate p2 = criteriaBuilder.like(custIndustry,"%学院");
                // 将多个查询条件组合到一起，组合（满足条件1并且满足条件二，）
//                Predicate predicate =  criteriaBuilder.and(p1,p2);  // 以于的方式拼接多个查询条件
                Predicate predicate =  criteriaBuilder.or(p1,p2); // 以或的方式拼接多个查询条件
                return predicate;
            }
        };
        List<Customer> customer = specificationTest.findAll(spec);
        System.out.println("customer = " + customer);
    }
    // jdk8改写 testSpec2
    @Test
    public void testSpec2_jdk8(){
        Customer customer = specificationTest.findOne((root,cquery,cb)
                -> cb.or(cb.like(root.get("custName"),"唐三")
                ,cb.like(root.get("custIndustry"),"诺丁城学院")
        )).get();
        System.out.println("customer = " + customer);
    }
    /**
     *  模糊查询 排序
     *   equal:直接到path对象（属性），然后比较即可
     *   gt,lt,ge,le,like:得到path对象，根据path 指定path 指定比较的参数类型，再去进行比较
     *   指定参数类型：path.as(类型的字节码对象)
     */
    @Test
    public void testSpec3(){
        /**
         *构造查询条件
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                // 构造查询
                // 1.构造客户名的精准匹配查询
                Predicate p1 = criteriaBuilder.like(custName.as(String.class),"_三");
                Predicate p2 = criteriaBuilder.like(custIndustry.as(String.class),"%学院");
                // 将多个查询条件组合到一起，组合（满足条件1并且满足条件二，）
//                Predicate predicate =  criteriaBuilder.and(p1,p2);  // 以于的方式拼接多个查询条件
                Predicate predicate =  criteriaBuilder.or(p1,p2); // 以或的方式拼接多个查询条件
                return predicate;
            }
        };
//        List<String> list = new ArrayList<>();
//        list.add("custId");
        List<Customer> customer = specificationTest.findAll(spec, Sort.by(Sort.Direction.DESC,"custId"));
        System.out.println("customer = " + customer);
    }
    // jdk8改写 testSpec3
    @Test
    public void testSpec3_jdk8(){
        List<Customer> customers = specificationTest.findAll((root,cquery,cb) ->
                 cb.or(cb.like(root.get("custName"),"_三")
                ,cb.like(root.get("custIndustry"),"%学院")
        ),Sort.by(Sort.Direction.DESC,"custId"));

        System.out.println("customer = " + customers);
    }



    /**
     * // 分页查询
     * findAll(Specification,Pageable)
     * Pageable:分页参数，查询的页码，每页查询的条件
     * 返回  Page(springDataJpa为我们封装好的pageBean对象，数据列表，工条件)
     */
    @Test
    public void testSpec4(){
        Specification spec = null;
        /**
         * pageRequest对象是Pageable接口的实现类
         * 创建pageRequest的过程中，需要调用他的构造方法传入两个参数
         * 第一个参数：当前查询的页数(从0开始)
         * 第二个参数：每页查询的数量
         */
        Pageable pageable = PageRequest.of(0,2, Sort.by(Sort.Direction.DESC,"custId"));
        Page<Customer> cs =  specificationTest.findAll(spec,pageable);
        long eles = cs.getTotalElements();
        System.out.println("eles = " + eles);
        int pages = cs.getTotalPages();
        System.out.println("pages = " + pages);
        List<Customer> c =  cs.getContent();
        System.out.println("c = " + c);
    }
    // jdk8改写 testSpec4
    @Test
    public void testSpec4_jdk8(){
        Page<Customer> pages = specificationTest.findAll((root,cquery,cb) ->
                cb.or(cb.like(root.get("custName"),"_三")
                        ,cb.like(root.get("custIndustry"),"%学院")
                ),
                PageRequest.of(0,2,Sort.by(Sort.Direction.DESC,"custId")));

        System.out.println("customer = " + pages.getContent()); // 内容

        System.out.println("pages.getTotalElements() = " + pages.getTotalElements()); // 共多少条

        System.out.println("pages.getTotalPages() = " + pages.getTotalPages()); // 有多少页

        System.out.println("pages.getNumberOfElements() = " + pages.getNumberOfElements());
    }
    /**
     * 根据条件统计个数
     */
    @Test
    public void testCount(){
        /**
         *构造查询条件
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                // 构造查询
                // 1.构造客户名的精准匹配查询
                Predicate p1 = criteriaBuilder.like(custName.as(String.class),"_三");
                Predicate p2 = criteriaBuilder.like(custIndustry.as(String.class),"%学院");
                // 将多个查询条件组合到一起，组合（满足条件1并且满足条件二，）
//                Predicate predicate =  criteriaBuilder.and(p1,p2);  // 以于的方式拼接多个查询条件
                Predicate predicate =  criteriaBuilder.or(p1,p2); // 以或的方式拼接多个查询条件
                return predicate;
            }
        };
        long count = specificationTest.count(spec);
        System.out.println("count = " + count);
    }

    // jdk8改写 testCount
    @Test
    public void testCount_jdk8(){
        long count = specificationTest.count((root,cquery,cb) ->
                        cb.or(
                                cb.like(root.get("custName"),"_三")
                                ,cb.like(root.get("custIndustry"),"%学院")
                        ));

        System.out.println("count = " + count); //
    }

}
