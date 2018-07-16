/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 玖富时代</p>
 * @author penghuari
 * @version 1.0
 */
package com.phr.common.extend;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import com.phr.common.utils.StringUtils;

@SuppressWarnings("unchecked")
@Component
public class SpringContextHolder implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;
	 
    
    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
    @Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
    	SpringContextHolder.applicationContext = applicationContext;

	}
 
    
	public static <T> T getBean(String beanName, Class<T> clazz) {
		if (null == beanName || "".equals(beanName.trim())) {
			return null;
		}
		if (clazz == null) {
			return null;
		} 
		return (T) applicationContext.getBean(beanName, clazz);
	}


	public static void publishEvent(ApplicationEvent event) {
		if (event == null) { 
			return;
		}
		applicationContext.publishEvent(event);
	}
    /**
     * 取得存储在静态变量中的ApplicationContext.
     * @return
     * @author penghuari
     * @createDate 2018年1月26日
     * @updateDate
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }
     
    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * @param name
     * @return
     * @author penghuari
     * @createDate 2018年1月26日
     * @updateDate
     */
    public static <T> T getBean(String name) {
    	if(StringUtils.isNull(name)) {
    		return null;
    	}
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }
     

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * 如果有多个Bean符合Class, 取出第一个.
     * @param clazz
     * @return
     * @author penghuari
     * @createDate 2018年1月26日
     * @updateDate
     */
	public static <T> T getBean(Class<T> clazz) {
    	if (clazz == null) {
    		return null;
    	}
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }
 
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }
}
