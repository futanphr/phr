package com.phr.common.redis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.phr.common.extend.SpringContextHolder;
import com.phr.common.redis.RedisUtils.SerializeUtil;
import com.phr.common.utils.StringUtils;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisClusterUtils {

	private static Logger logger = LoggerFactory.getLogger(RedisClusterUtils.class);

	private static String default_charset = "utf-8";

	private static Integer timeout = 604800;

	/**
	 * 获取getRedisTemplate()对象
	 *
	 * @return
	 * @author penghuari
	 * @createDate 2018年1月29日
	 * @updateDate
	 */

	static {

		RedisClusterConfig redisClusterConfig = SpringContextHolder.getBean(RedisClusterConfig.class);
		String[] cNodes = redisClusterConfig.getNodes().split(",");
		Set<HostAndPort> nodes = new HashSet<>();
		// 分割出集群节点
		for (String node : cNodes) {
			String[] hp = node.split(":");
			nodes.add(new HostAndPort(hp[0], Integer.parseInt(hp[1])));
		}

		jedisCluster = new JedisCluster(nodes);
	}

	private static JedisCluster jedisCluster;

	public static JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	// =============================common============================

	/**
	 * 指定缓存失效时间
	 *
	 * @param key
	 *            键
	 * @param time
	 *            时间(秒)
	 * @return
	 */
	public static boolean expire(String key, int time) {
		try {
			if (time > 0) {
				// getRedisTemplate().expire(getNameSpace(key), time,
				// TimeUnit.SECONDS);
				getJedisCluster().expire(getNameSpace(key), time);
			}
			return true;
		} catch (Exception e) {
			logger.error("is error:", e);
			return false;
		}
	}

	/**
	 * 判断key是否存在
	 *
	 * @param key
	 *            键
	 * @return true 存在 false不存在
	 */
	public static boolean exists(String key) {
		try {
			// return getRedisTemplate().hasKey(getNameSpace(key));

			return getJedisCluster().exists(getNameSpace(key));
		} catch (Exception e) {
			logger.error("is error:", e);
			return false;
		}
	}

	/**
	 * 删除缓存
	 *
	 * @param key
	 *            可以传一个值 或多个
	 */
	public static void del(String... keys) {
		if (keys != null && keys.length > 0) {
			if (keys.length == 1) {
				// getRedisTemplate().delete(getNameSpace(keys[0]));
				getJedisCluster().del(getNameSpace(keys[0]));
			} else {
				for (String key : keys) {
					getJedisCluster().del(getNameSpace(key));
				}

			}
		}
	}

	/**
	 * 根据表达式返回key集合（慎用，效率较低）
	 *
	 * @param pattern
	 *            表达式
	 * @return
	 * @author penghuari
	 * @createDate 2018年2月1日
	 * @updateDate
	 */
	// public static Set<String> keys(String pattern) {
	// return getRedisTemplate().keys(pattern);
	// }

	/**
	 * 批量删除
	 *
	 * @param keys
	 * @author penghuari
	 * @createDate 2018年2月1日
	 * @updateDate
	 */
	public static void delete(Collection<String> keys) {
		for (String key : keys) {
			// getRedisTemplate().delete(getNameSpace(key));
			getJedisCluster().del(getNameSpace(key));
		}
	}

	// ============================String=============================

	/**
	 * 普通缓存获取
	 *
	 * @param key
	 *            键
	 * @return 值
	 */
	public static String get(String key) {
		// return key == null ? null :
		// getRedisTemplate().opsForValue().get(getNameSpace(key));
		return key == null ? null : getJedisCluster().get(getNameSpace(key));

	}

	@SuppressWarnings("unchecked")
	public static <T> T get(String key, Class<?> clz) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		try {
			String value = getJedisCluster().get(getNameSpace(key));
			if (value != null && clz != null) {
				return (T) JSON.parseObject(value, clz);
			} else {
				return (T) value;
			}
		} catch (Exception e) {
			logger.error("get cache exception，key is ： " + key, e);
			return null;
		}
	}

	/**
	 * 普通缓存放入
	 *
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return true成功 false失败
	 */
	public static boolean set(String key, String value) {
		try {
			getJedisCluster().set(getNameSpace(key), value);
			 
			// getRedisTemplate().opsForValue().set(getNameSpace(key), value);
			return true;
		} catch (Exception e) {
			logger.error("is error:", e);
			return false;
		}

	}

	// private static RedisSerializer<String> getSerializer() {
	// return getRedisTemplate().getStringSerializer();
	// }

	/**
	 * 字符串类型:根据key设置value值,如果key中的value存在,那么返回false
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public static Boolean setnx(final String key, final String value) {
		// RedisSerializer<String> serializer = getSerializer();
		// byte keys[] = serializer.serialize(getNameSpace(key));
		// byte values[] = serializer.serialize(value);
		// return
		// getRedisTemplate().getConnectionFactory().getConnection().setNX(keys,
		// values);

		if (getJedisCluster().setnx(getNameSpace(key), value).equals(1L)) {
			 
			return true;
		}

		return false;
	}

	// public static Long delByteKey(final String key) {
	//
	// RedisSerializer<String> serializer = getSerializer();
	// byte keys[] = serializer.serialize(getNameSpace(key));
	//
	// return
	// getRedisTemplate().getConnectionFactory().getConnection().del(keys);
	//
	// }

	/**
	 * 字符串类型:设置key和value的超时时间(设置成String返回类型,不然要设置成Void)
	 *
	 * @param key
	 * @param timeout
	 * @param value
	 * @return
	 */
	public static void setex(final String key, final String value, final int timeout) {
		// RedisSerializer<String> serializer = getSerializer();
		// byte keys[] = serializer.serialize(getNameSpace(key));
		// byte values[] = serializer.serialize(value);
		// getRedisTemplate().getConnectionFactory().getConnection().setEx(keys,
		// timeout, values);

		getJedisCluster().setex(getNameSpace(key), timeout, value);
		 
	}

	/**
	 * 普通缓存放入并设置时间
	 *
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param time
	 *            时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return true成功 false 失败
	 */
	public static boolean set(String key, String value, int time) {
		try {

			getJedisCluster().set(getNameSpace(key), value);
			if (time > 0) {
				// getRedisTemplate().opsForValue().set(getNameSpace(key),
				// value, time, TimeUnit.SECONDS);

				getJedisCluster().expire(getNameSpace(key), time);
			}
			return true;
		} catch (Exception e) {
			logger.error("is error:", e);
			return false;
		}
	}

	/**
	 * 递增 1
	 *
	 * @param key
	 *            键
	 * @return
	 */
	public static long incr(String key) {
		return incr(key, 1L);
	}

	/**
	 * 递增
	 *
	 * @param key
	 *            键
	 * @param by
	 *            要增加几(大于0)
	 * @return
	 */
	public static long incr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递增因子必须大于0");
		}

		// return getRedisTemplate().opsForValue().increment(getNameSpace(key),
		// delta);

		Long l = getJedisCluster().incrBy(getNameSpace(key), delta);
		 
		return l;
	}

	/**
	 * 递减
	 *
	 * @param key
	 *            键
	 * @return
	 */
	public static long decr(String key) {
		return decr(getNameSpace(key), -1L);

	}

	/**
	 * 递减
	 *
	 * @param key
	 *            键
	 * @param by
	 *            要减少几(小于0)
	 * @return
	 */
	public static long decr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递减因子必须大于0");
		}
		// return getRedisTemplate().opsForValue().increment(getNameSpace(key),
		// -delta);

		return getJedisCluster().decrBy(getNameSpace(key), delta);

	}

	// ================================Map=================================

	public static Object getObj(String key) {
		byte[] result = null;
		try {
			// jedis = RedisPool.getJedis();
			// result = jedis.get(getNameSpace(key).getBytes(default_charset));

			result = getJedisCluster().get(getNameSpace(key).getBytes(default_charset));
			 
		} catch (Exception e) {
			logger.error("get key:{} error", getNameSpace(key), e);
			return result;
		}
		if (null != result) {
			return SerializeUtil.unserialize(result);
		}
		return result;
	}

	public static String setObj(String key, Object value) {
		String result = null;

		try {
			// jedis = RedisPool.getJedis();
			// result = jedis.set(getNameSpace(key).getBytes(default_charset),
			// SerializeUtil.serialize(value));
			result = getJedisCluster().set(getNameSpace(key).getBytes(default_charset), SerializeUtil.serialize(value));
			getJedisCluster().expire(getNameSpace(key).getBytes(default_charset), timeout);
		} catch (Exception e) {
			logger.error("set key:{} value:{} error", getNameSpace(key), value, e);
			return result;
		}

		return result;
	}

	public static String getNameSpace(String key) {
		return new StringBuffer(RedisPool.getCacheNameSpace()).append("_").append(key).toString();

	}

}
