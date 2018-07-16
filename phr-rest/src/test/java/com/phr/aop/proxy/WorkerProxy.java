package com.phr.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class WorkerProxy implements InvocationHandler{
	private Object target;

	public Object Bind(Object target) {
		this.target=target;
		Object object=Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
		return object;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("开始");
		method.invoke(target, args);
		System.out.println("结束");
		return null;
	}

}
