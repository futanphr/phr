package com.phr.rest.entity.request;

import java.util.Date;

import com.phr.common.annotation.ValidataFieldAnnotation;
import com.phr.core.entity.RequestBaseEntity;

import io.swagger.annotations.ApiModelProperty;

@SuppressWarnings("serial")
public class RequestUserEntity extends RequestBaseEntity{
	@ApiModelProperty(value="用户id",required=true)
	@ValidataFieldAnnotation(required=true)
	private Long userId;
	private int cityId;
	private String userName;
	private int age;
	private Date birth;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	

}
