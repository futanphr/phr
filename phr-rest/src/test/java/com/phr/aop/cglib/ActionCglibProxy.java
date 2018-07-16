package com.phr.aop.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ActionCglibProxy implements MethodInterceptor {

    private String name;

    private Enhancer enhancer = new Enhancer();

    public ActionCglibProxy(String name) {
        super();
        this.name = name;
    }

    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        if ("zhyonk".equals(name)) {
            Object result = proxy.invokeSuper(obj, args);
            return result;
        }
        System.out.println(name + "你不可以抽烟哦");
        return null;
    }
    public Action getAction(){
        enhancer.setSuperclass(Action.class);
        enhancer.setCallback(this);
        return (Action) enhancer.create();
    }
}