package com.phr.common.constants;
/**
 * <p>Title:</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 玖富时代</p>
 * @author: penghuari
 * @version 1.0
 */
public class Constants {
	private Constants() {
	}
	public static final String DEFAULT_STR = "";
	/**男*/
	public static final String MALE = "1";
	/**女*/
	public static final String FEMALE = "0";
	/**
	 * http 请求默认连接时间
	 */
	public static final int DEFAULT_CONNECT_TIMEOUT=3000;
	/**
	 * http 请求默认读取时间
	 */
	public static final int DEFAULT_READ_TIMEOUT=20000;
	/**
	 * 分页查询默认起始页
	 */
	public static final int DEFAULT_PAGE_NUMBER=1;
	/**
	 * 分页查询默认查询多少条
	 */
	public static final int DEFAULT_PAGE_SIZE=10;
	/**
	 * 分页查询最大值
	 */
	public static final int DEFAULT_MAXSIZE = 200;
	/**
	 * 默认系统标识
	 */
	public static final String  DEFAULT_SYSTEM = "API";
	
	/**
	 * 授权剩余时间
	 */
	public static final int AUTHORIZE_SECOND = 180;
}

