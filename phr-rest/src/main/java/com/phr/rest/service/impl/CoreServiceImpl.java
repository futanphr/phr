package com.phr.rest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.phr.common.dto.ResultData;
import com.phr.common.redis.RedisLock;
import com.phr.common.utils.ResultDataUtil;
import com.phr.rest.entity.request.RequestOrderEntity;
import com.phr.rest.service.CoreService;
@Service("coreService")
public class CoreServiceImpl implements CoreService{
	private Logger logger=LoggerFactory.getLogger(CoreServiceImpl.class);

	@Override
	@Transactional(transactionManager = "mybatisTransactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public ResultData createOrder(RequestOrderEntity requestEntity) {
		ResultData resultData = null;
		RedisLock lock = new RedisLock(requestEntity.getOrderId().toString());
		
			// 1.判断是否已经加锁
			System.out.println("lock.isLock()===>"+lock.isLock());
	
			if (lock.isLock()) {
				return ResultDataUtil.errorResult("分布式锁已存在，请勿重复操作");// 
			}
		try {
			lock.lock();
			//模拟业务处理3秒
			System.out.println(Thread.currentThread().getName()+"===>打印日志开始");
			Thread.sleep(20000);
			System.out.println(Thread.currentThread().getName()+"===>打印日志结束");
			
		} catch (Exception e) {
			logger.error("===>分布式锁出现异常：",e);
			return resultData;
		} finally {
			lock.unlock();
		}

		return ResultDataUtil.successResult("操作成功!");// 
	}

}
