package com.phr.rest.biz.entity;

 import java.io.Serializable; 
import java.util.Date;
/**
 *
 * @类介绍 
 * @time 2018年07月13日 14:56:52
 * @version 1.0
 *
 **/

@SuppressWarnings("serial")
public class UserEntity implements Serializable  {
  
    
    /**
     * 
     */
    private Long id;
    /**
     * 
     */
    private Long userId;
    /**
     * 
     */
    private Integer cityId;
    /**
     * 
     */
    private String userName;
    /**
     * 
     */
    private Integer age;
    /**
     * 
     */
    private Date birth;

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
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    /**
     * 获取
     */
    public Long getUserId() {
        return this.userId;
    }
	/**
	 * 设置
	 */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    /**
     * 获取
     */
    public Integer getCityId() {
        return this.cityId;
    }
	/**
	 * 设置
	 */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 获取
     */
    public String getUserName() {
        return this.userName;
    }
	/**
	 * 设置
	 */
    public void setAge(Integer age) {
        this.age = age;
    }
    /**
     * 获取
     */
    public Integer getAge() {
        return this.age;
    }
	/**
	 * 设置
	 */
    public void setBirth(Date birth) {
        this.birth = birth;
    }
    /**
     * 获取
     */
    public Date getBirth() {
        return this.birth;
    }

}