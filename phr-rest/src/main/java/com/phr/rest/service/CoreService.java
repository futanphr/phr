package com.phr.rest.service;

import com.phr.common.dto.ResultData;
import com.phr.rest.entity.request.RequestOrderEntity;
/**
 * 
 * 
 * @Title: QuotaService.java
 * @Package com.jfcf.service
 * @Description: 额度接口
 * @author zhi19861117@126.com
 * @date 上午11:55:21
 * @version V1.0
 */
public interface CoreService {


	/**
	 * 额度扣减
	 * 
	 * @param requestEntity
	 * @return
	 */
	ResultData createOrder(RequestOrderEntity requestEntity);

	

}
