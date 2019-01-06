package com.phr.rest.biz.entity;

 import java.io.Serializable; 
import java.util.Date;
import java.math.BigDecimal;
/**
 *
 * @类介绍 
 * @time 2019年01月06日 23:11:47
 * @version 1.0
 * @description 数据库表对应的实体类，类中属性与数据库字段对应
 **/

@SuppressWarnings("serial")
public class PhrOrderEntity implements Serializable  {
    /**
     * 
     */
    private Long id;
    /**
     * 
     */
    private Long orderId;
    /**
     * 
     */
    private String mobile;
    /**
     * 
     */
    private BigDecimal amt;
    /**
     * 
     */
    private Date createTime;
    /**
     * 
     */
    private Date updateTime;
	/**
	 * 设置
	 */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取
     */
    public Long getId() {
        return this.id;
    }
	/**
	 * 设置
	 */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    /**
     * 获取
     */
    public Long getOrderId() {
        return this.orderId;
    }
	/**
	 * 设置
	 */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * 获取
     */
    public String getMobile() {
        return this.mobile;
    }
	/**
	 * 设置
	 */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
    /**
     * 获取
     */
    public BigDecimal getAmt() {
        return this.amt;
    }
	/**
	 * 设置
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取
     */
    public Date getCreateTime() {
        return this.createTime;
    }
	/**
	 * 设置
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * 获取
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 以下代码是采用设计模式中的建造者模式，链式编程
     * 例如创建一个对象，并且给对象的id赋值100，
     * 我们可以这么写：PhrOrderEntity entity=new PhrOrderEntity.Builder.setId(100).build();
     */
    public static class Builder{
    private Long id;
    private Long orderId;
    private String mobile;
    private BigDecimal amt;
    private Date createTime;
    private Date updateTime;

    public Builder setId(Long id) {
        this.id = id;
        return this;
    }
    public Builder setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }
    public Builder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }
    public Builder setAmt(BigDecimal amt) {
        this.amt = amt;
        return this;
    }
    public Builder setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
    public Builder setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public PhrOrderEntity build(){
        return new PhrOrderEntity(this);
    }
    }

    public PhrOrderEntity(Builder builder){
      this.id = builder.id;
      this.orderId = builder.orderId;
      this.mobile = builder.mobile;
      this.amt = builder.amt;
      this.createTime = builder.createTime;
      this.updateTime = builder.updateTime;
    }

}