package com.emon.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 1.实体类和表的映射关系
 * @Entity
 * @Table
 * 2.类中属性和标中字段的映射关系
 * @Id
 * @GeneratedValue
 * @Column
 */

@Entity
@Table(name="cst_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="cust_id")
    private Long custId;
    @Column(name="cust_name")
    private String custName;
    @Column(name="cust_source")
    private String custSource;
    @Column(name="cust_industry")
    private String custIndustry;
    @Column(name="cust_level")
    private String custLevel;
    @Column(name="cust_address")
    private String custAddress;
    @Column(name="cust_phone")
    private String custPhone;

    /**
     * 删除主表数据：
     *
     * 	有从表数据
     *   1、在默认情况下，它会把外键字段置为null，然后删除主表数据。如果在数据库的表                结构上，外键字段有非空约束，默认情况就会报错了。
     *   2、如果配置了放弃维护关联关系的权利，则不能删除（与外键字段是否允许为null，		没有关系）因为在删除时，它根本不会去更新从表的外键字段了。
     *   3、如果还想删除，使用级联删除引用
     *
     * 	没有从表数据引用：随便删
     *
     *级联操作
     * cascade:配置级联操作
     * 		CascadeType.MERGE	级联更新
     * 		CascadeType.PERSIST	级联保存：
     * 		CascadeType.REFRESH 级联刷新：
     * 		CascadeType.REMOVE	级联删除：
     * 		CascadeType.ALL		包含所有
     */
    // 配置客户和联系人之间的关系 一对多关系
    // 放弃外键维护，参照对方来做，mappedBy填写 linkMan 的配置的对应属性名
    // fetch 配置关联对象的加载方式
    // EAGER：立即加载
    // Lazy :延迟加载
    @OneToMany(mappedBy="customer",cascade = CascadeType.ALL)
    private Set<LinkMan> linkMans = new HashSet<>();

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public Set<LinkMan> getLinkMans() {
        return linkMans;
    }

    public void setLinkMans(Set<LinkMan> linkMans) {
        this.linkMans = linkMans;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custAddress='" + custAddress + '\'' +
                ", custPhone='" + custPhone + '\'';
    }
}
