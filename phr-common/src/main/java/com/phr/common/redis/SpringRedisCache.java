package com.phr.common.redis;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;
import com.phr.common.extend.SpringContextHolder;

public class SpringRedisCache implements Cache {
	private static Logger logger = LoggerFactory.getLogger(SpringRedisCache.class);

	public SpringRedisCache() {

	}

	public SpringRedisCache(String name) {
		this.name = name;
	}

	public String name = null;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Object getNativeCache() {
		return null;
	}

	private static String cacheStr = "cache:";

	private static RedisTemplate<String, Object> redisTemplate;

	private static RedisTemplate<String, Object> getRedisTemplate() {
		if (redisTemplate == null) {
			redisTemplate = SpringContextHolder.getBean(RedisTemplate.class);
		}
		return redisTemplate;
	}

	@Override
	public ValueWrapper get(Object key) {
		try {

			String keyBuffer = new StringBuffer(cacheStr).append(getName()).append(key).toString();

			Object values = RedisClusterUtils.getObj(keyBuffer);
//			Object values = redisTemplate.opsForValue().get(keyBuffer);

			logger.info("SpringRedisCache method[get] key:{},value:{}", keyBuffer, JSON.toJSONString(values));
			if (values == null) {
				return null;
			}

			return new SimpleValueWrapper(values);

		} catch (Exception e) {
			logger.error("集成spring cache 异常", e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Object key, Class<T> type) {
		String keyBuffer = new StringBuffer(cacheStr).append(getName()).append(key).toString();

		Object object = RedisClusterUtils.get(keyBuffer, type);
//		Object object = getRedisTemplate().opsForValue().get(key);

		logger.info("SpringRedisCache method[get] key:{},value:{}", keyBuffer.toString(), JSON.toJSONString(object));
		return (T) object;
	}

	@Override
	public void put(Object key, Object value) {
		StringBuffer keyBuffer = new StringBuffer(cacheStr);
		keyBuffer.append(getName()).append(key);
		logger.info("method[put] key:{},value:{}", keyBuffer, JSON.toJSONString(value));
		RedisClusterUtils.setObj(keyBuffer.toString(), value);

//		getRedisTemplate().opsForValue().set(keyBuffer.toString(), value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void evict(Object key) {
		if (key instanceof ArrayList) {
			ArrayList<Object> list = (ArrayList<Object>) key;
			for (Object object : list) {
				String keyBuffer = new StringBuffer(cacheStr).append(getName()).append(object).toString();
				logger.info("SpringRedisCache method[evict] key:{}", keyBuffer.toString());
				RedisClusterUtils.del(keyBuffer);
//				getRedisTemplate().delete(keyBuffer);
			}
		} else {
			String keyBuffer = new StringBuffer(cacheStr).append(getName()).append(key).toString();
			logger.info("SpringRedisCache method[evict] key:{}", keyBuffer.toString());
			RedisClusterUtils.del(keyBuffer);
//			getRedisTemplate().delete(keyBuffer);
		}
	}

	@Override
	public void clear() {
//		StringBuffer key = new StringBuffer();
//		key.append("*").append(cacheStr).append(getName()).append("*");

//		logger.info("SpringRedisCache method[clear] key:{}", key.toString());
//		Set<String> keys = RedisClusterUtils.getKeys(key.toString());
//		for (String k : keys) {
//			RedisClusterUtils.del(false, k);
//
//		}
	}

	@Override
	public <T> T get(Object key, Callable<T> valueLoader) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueWrapper putIfAbsent(Object key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

}
