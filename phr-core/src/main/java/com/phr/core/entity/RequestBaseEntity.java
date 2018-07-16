package com.phr.core.entity;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 
 * 
 * @author penghuari
 * @time 2018年5月16日上午9:42:55
 * @email futanphrs@126.com
 * @version 1.0
 * @类介绍 请求基础类
 */
@SuppressWarnings("serial")
public class RequestBaseEntity implements Serializable {

	public String toString() {
		return JSON.toJSONString(this);
	}

}
