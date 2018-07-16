package com.phr.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.phr.common.extend.SpringContextHolder;
import com.phr.common.utils.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

	private static Logger logger = LoggerFactory.getLogger(RedisPool.class);
	private static JedisPool pool;// jedis连接池
	private static Integer maxTotal = Integer.parseInt(getRedisPoolConfig().getPool().get("max-active")); // 最大连接数
	private static Integer maxIdle = Integer.parseInt(getRedisPoolConfig().getPool().get("max-idle"));// 在jedispool中最大的idle状态(空闲的)的jedis实例的个数
	private static Integer minIdle = Integer.parseInt(getRedisPoolConfig().getPool().get("min-idle"));// 在jedispool中最小的idle状态(空闲的)的jedis实例的个数

	private static String redisIp = getRedisPoolConfig().getHostName();
	private static String pwd = getRedisPoolConfig().getPassword();
	private static Integer redisPort = getRedisPoolConfig().getPort();
	private static Integer timeout = getRedisPoolConfig().getTimeout();
	private static Integer dbIndex = getRedisPoolConfig().getDatabase();
	private static String cacheNameSpace = null;

	private static synchronized void initPool() {
		JedisPoolConfig config = new JedisPoolConfig();

		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);
		config.setMaxWaitMillis(timeout);

		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);

		config.setTestWhileIdle(true);
		config.setBlockWhenExhausted(true);// 连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。

		if (StringUtils.isNotNull(pwd)) {
			pool = new JedisPool(config, redisIp, redisPort, timeout, pwd, dbIndex);
		} else {
			pool = new JedisPool(config, redisIp, redisPort, timeout, null, dbIndex);
		}

		cacheNameSpace = getRedisPoolConfig().getCacheName();
	}

	static {
		initPool();
	}

	public static Jedis getJedis() {
		return pool.getResource();
	}

	public static void close(Jedis jedis) {
		try {
			if (jedis != null) {
				jedis.close();
			}
		} catch (Exception e) {
			logger.error("return redis resource exception", e);
		}
	}

	public static String getCacheNameSpace() {
		return cacheNameSpace;
	}

	public static RedisPoolConfig getRedisPoolConfig() {

		return SpringContextHolder.getBean("redisPoolConfig", RedisPoolConfig.class);
	}

}
