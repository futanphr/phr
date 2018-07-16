package com.phr.rest.controller;

import com.phr.common.redis.RedisClusterUtils;
import com.phr.common.redis.RedisLock;

public class ThreadLock implements Runnable{

	@Override
	public void run() {
		RedisLock lock = new RedisLock("123");
		

			// 1.判断是否已经加锁
			if (lock.isLock()) {
				System.out.println(Thread.currentThread().getName()+"   8分钟内不允许重发添加添加");// 
				return;
			}
			try {
			System.out.println(Thread.currentThread().getName()+"===>正在上锁");
			lock.lock();
			System.out.println(Thread.currentThread().getName()+"===>上锁成功");
			//模拟业务处理3秒
			System.out.println(Thread.currentThread().getName()+"===>业务处理开始");
			Thread.sleep(20000);
			System.out.println(Thread.currentThread().getName()+"===>业务处理结束");
			
		} catch (Exception e) {
		} finally {
			lock.unlock();
	}}}
