package com.phr.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * 
 * @author yangzhi
 * @time 2015年11月3日上午9:56:41
 * @email zhi19861117@126.com
 * @version 1.0
 * @类介绍
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidataFieldAnnotation {
	/**
	 * 验证该字段为必填字段
	 * 
	 * @return
	 */
	public boolean required() default true;

}
