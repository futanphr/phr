package com.phr.rest.biz.entity;

 import java.io.Serializable; 
import java.util.Date;
import java.math.BigDecimal;
/**
 *
 * @类介绍 
 * @time 2019年01月06日 00:00:23
 * @version 1.0
 *
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

}