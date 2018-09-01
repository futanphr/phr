package com.phr.rest.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.phr.common.dto.ResultData;
import com.phr.common.utils.ResultDataUtil;
import com.phr.core.entity.PageInfo;
import com.phr.core.entity.PageInfoBak;
import com.phr.rest.biz.entity.PhrOrderEntity;
import com.phr.rest.biz.entity.PhrQuotaEntity;
import com.phr.rest.biz.service.PhrQuotaService;
import com.phr.rest.entity.request.RequestQuotaEntity;
import com.phr.rest.mq.MqSenderImpl;
import com.phr.rest.service.CoreService;

@RestController
@RequestMapping("/quota")
public class QuotaController {

	@Autowired
	private PhrQuotaService phrQuotaService;
	@Autowired
	private CoreService coreService;
	@Resource
	private MqSenderImpl mqSenderImpl;
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public ResultData insert(RequestQuotaEntity requestOrderEntity) {
		PhrQuotaEntity orderEntity = new PhrQuotaEntity();
		orderEntity.setCustomerId(requestOrderEntity.getCustomerId());
		orderEntity.setMobile(requestOrderEntity.getMobile());
		orderEntity.setAmt(requestOrderEntity.getAmt());
		orderEntity.setCreateTime(new Date());
		ResultData resultData=ResultDataUtil.successResult("初始化成功！"); 
		try {
//		phrOrderService.insertSelective(orderEntity);
		
		// MQ推送额度
        Map<String, Object> par = new HashMap<String, Object>();
        par.put("customerId", 1313131311);
        par.put("mobile", "13120039229");
        par.put("quota", 99999);
       //mqSenderImpl.sendMessage(FastJsonUtils.toJson(par), "bangsheng_data_notice_topic", "auth_tags", 1313131311 + "");//测试rocketMQ
//       resultData=coreService.createOrder(requestOrderEntity);//测试分布式锁
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("id", 1);
        PhrQuotaEntity phrQuotaEntity=phrQuotaService.selectByKeys(params);
        System.out.println(JSON.toJSONString(phrQuotaEntity));
        /* PageInfo<PhrQuotaEntity> list= phrQuotaService.getListByPage(params,requestOrderEntity.getPageNum(),requestOrderEntity.getPageSize());
        System.out.println(JSON.toJSONString(list));
        for(PhrQuotaEntity entity:list.getItems()) {
        	System.out.println(JSON.toJSONString(entity));
        }*/
        //测试分页查询
        //phrOrderService.selectByOrderId(requestOrderEntity.getOrderId());//测试springcache缓存
		} catch (Exception e) {
			e.printStackTrace();
		}
       
//        ResultData resultData=ResultDataUtil.successResult("操作成功！");
		return resultData;
	}
}
