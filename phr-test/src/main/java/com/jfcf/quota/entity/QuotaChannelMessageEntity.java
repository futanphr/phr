package com.jfcf.quota.entity;

 import java.io.Serializable; 
import java.util.Date;
import java.math.BigDecimal;
/**
 *
 * @类介绍 接收渠道推送的额度
 * @time 2018年07月30日 15:19:46
 * @version 1.0
 *
 **/

@SuppressWarnings("serial")
public class QuotaChannelMessageEntity implements Serializable  {
  
    
    /**
     * 
     */
    private Long id;
    /**
     * 客户号
     */
    private Long customerId;
    /**
     * 额度流转状态,1待自动审核，2定时跑批中，3进入人工信审，4信审退回，5信审通过，6信审拒绝, 7审核异常
     */
    private String appStatus;
    /**
     * 冻结标识，0未冻结，1已冻结
     */
    private String freezeStatus;
    /**
     * 总额度
     */
    private BigDecimal quota;
    /**
     * 彩虹评级
     */
    private String gradeLevel;
    /**
     * 火眼分
     */
    private String fireScore;
    /**
     * 渠道号
     */
    private String proId;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 状态,0初始化，1处理成功
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 备注
     */
    private String remark;

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
	 * 设置额度流转状态,1待自动审核，2定时跑批中，3进入人工信审，4信审退回，5信审通过，6信审拒绝, 7审核异常
	 */
    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }
    /**
     * 获取额度流转状态,1待自动审核，2定时跑批中，3进入人工信审，4信审退回，5信审通过，6信审拒绝, 7审核异常
     */
    public String getAppStatus() {
        return this.appStatus;
    }
	/**
	 * 设置冻结标识，0未冻结，1已冻结
	 */
    public void setFreezeStatus(String freezeStatus) {
        this.freezeStatus = freezeStatus;
    }
    /**
     * 获取冻结标识，0未冻结，1已冻结
     */
    public String getFreezeStatus() {
        return this.freezeStatus;
    }
	/**
	 * 设置总额度
	 */
    public void setQuota(BigDecimal quota) {
        this.quota = quota;
    }
    /**
     * 获取总额度
     */
    public BigDecimal getQuota() {
        return this.quota;
    }
	/**
	 * 设置彩虹评级
	 */
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
    /**
     * 获取彩虹评级
     */
    public String getGradeLevel() {
        return this.gradeLevel;
    }
	/**
	 * 设置火眼分
	 */
    public void setFireScore(String fireScore) {
        this.fireScore = fireScore;
    }
    /**
     * 获取火眼分
     */
    public String getFireScore() {
        return this.fireScore;
    }
	/**
	 * 设置渠道号
	 */
    public void setProId(String proId) {
        this.proId = proId;
    }
    /**
     * 获取渠道号
     */
    public String getProId() {
        return this.proId;
    }
	/**
	 * 设置创建人
	 */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    /**
     * 获取创建人
     */
    public String getCreator() {
        return this.creator;
    }
	/**
	 * 设置状态,0初始化，1处理成功
	 */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * 获取状态,0初始化，1处理成功
     */
    public Integer getStatus() {
        return this.status;
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
	 * 设置修改时间
	 */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    /**
     * 获取修改时间
     */
    public Date getUpdatedTime() {
        return this.updatedTime;
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