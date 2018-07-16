package com.phr.common.mybatis;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TableSeg {
	/**
	 * 表名
	 * 
	 * @return
	 */
	public String tableName();

	/**
	 * 
	 * 分表类型
	 * 
	 * @return
	 */
	public int nums();

	/**
	 * 根据什么字段分表
	 * 
	 * @return
	 */
	public String shardBy();
}
