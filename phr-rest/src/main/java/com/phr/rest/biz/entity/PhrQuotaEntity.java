package com.phr.rest.biz.entity;

 import java.io.Serializable; 
import java.util.Date;
import java.math.BigDecimal;
/**
 *
 * @类介绍 
 * @time 2018年07月30日 17:52:37
 * @version 1.0
 *
 **/

@SuppressWarnings("serial")
public class PhrQuotaEntity implements Serializable  {
  
    
    /**
     * 主键id自增
     */
    private Long id;
    /**
     * 客户号
     */
    private Long customerId;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 金额
     */
    private BigDecimal amt;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 备注
     */
    private String remark;

	/**
	 * 设置主键id自增
	 */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取主键id自增
     */
    public Long getId() {
        return this.id;
    }
	/**
	 * 设置客户号
	 */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    /**
     * 获取客户号
     */
    public Long getCustomerId() {
        return this.customerId;
    }
	/**
	 * 设置手机号
	 */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * 获取手机号
     */
    public String getMobile() {
        return this.mobile;
    }
	/**
	 * 设置金额
	 */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }
    /**
     * 获取金额
     */
    public BigDecimal getAmt() {
        return this.amt;
    }
	/**
	 * 设置创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }
	/**
	 * 设置更新时间
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * 获取更新时间
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }
	/**
	 * 设置备注
	 */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * 获取备注
     */
    public String getRemark() {
        return this.remark;
    }

}