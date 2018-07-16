package com.phr.common.redis;

import com.phr.common.utils.StringUtils;

public class CacheUtil {
	/**
	 * 临时保存30秒
	 */
	public static final int SNAP_TIME_30 = 30;
	/**
	 * 临时保存60秒
	 */
	public static final int SNAP_TIME_60 = 60;
	/**
	 * 临时保存120秒
	 */
	public static final int SNAP_TIME_120 = 120;
	/**
	 * 临时保存180秒
	 */
	public static final int SNAP_TIME_180 = 180;
	/**
	 * 临时保存300秒
	 */
	public static final int SNAP_TIME_300 = 300;
	/**
	 * 临时保存600秒
	 */
	public static final int SNAP_TIME_600 = 600;
	/**
	 * 临时保存1800秒
	 */
	public static final int SNAP_TIME_1800 = 1800;
	/**
	 * 临时保存一小时
	 */
	public static final int SNAP_TIME_3600 = 3600;
	/**
	 * 临时保存一天
	 */
	public static final int SNAP_TIME_129600 = 129600;

	public static String getStr(String prefix, String key) {
		return RedisClusterUtils.get(getKey(prefix, key));
	}

	public static String getKey(String prefix, String key) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotNull(prefix)) {
			sb.append(prefix);
		}
		if (StringUtils.isNotNull(key)) {
			sb.append("_").append(key);
		}
		return sb.toString();
	}

	public static String getKey(String key) {
		return getKey(null, key);
	}

	public static void removeStr(String prefix, String key) {
		RedisClusterUtils.del(getKey(prefix, key));

	}

	public static void incr(String prefix, String key) {
		RedisClusterUtils.incr(getKey(prefix, key));
	}

	public static void setStr(String prefix, String key, String value, int exTime) {
		RedisClusterUtils.setex(getKey(prefix, key), value, exTime);
		
	}
}
