package com.phr.common.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.phr.common.utils.StringUtils;

import redis.clients.jedis.Jedis;

public class RedisUtils {
	private static String default_charset = "utf-8";
	private static Logger log = LoggerFactory.getLogger(RedisUtils.class);
	public static final String SCORE_MAX = "+inf";
	public static final String SCORE_MIN = "-inf";

	/**
	 * 设置key的有效期，单位是秒
	 * 
	 * @param key
	 * @param exTime
	 * @return
	 */
	public static Long expire(String key, int exTime) {
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.expire(getNameSpace(key), exTime);
		} catch (Exception e) {
			log.error("expire key:{} error", getNameSpace(key), e);
			RedisPool.close(jedis);
			return result;
		}
		RedisPool.close(jedis);
		return result;
	}

	/**
	 * set 带过期时间
	 * 
	 * @param key
	 * @param value
	 * @param exTime
	 * @return
	 */
	public static String setex(String key, String value, int exTime) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.setex(getNameSpace(key), exTime, value);
		} catch (Exception e) {
			log.error("setex key:{} value:{} error", getNameSpace(key), value, e);
			RedisPool.close(jedis);
			return result;
		}
		RedisPool.close(jedis);
		return result;
	}

	/**
	 * 设置key
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static String set(String key, String value) {
		Jedis jedis = null;
		String result = null;

		try {
			jedis = RedisPool.getJedis();
			result = jedis.set(getNameSpace(key), value);
		} catch (Exception e) {
			log.error("set key:{} value:{} error", getNameSpace(key), value, e);
			return result;
		} finally {
			RedisPool.close(jedis);
		}

		return result;
	}

	public static String setObj(String key, Object value) {
		Jedis jedis = null;
		String result = null;

		try {
			jedis = RedisPool.getJedis();
			result = jedis.set(getNameSpace(key).getBytes(default_charset), SerializeUtil.serialize(value));
		} catch (Exception e) {
			log.error("set key:{} value:{} error", getNameSpace(key), value, e);
			return result;
		} finally {
			RedisPool.close(jedis);
		}

		return result;
	}

	/**
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean setnx(String key, String value) {
		Jedis jedis = null;

		try {
			jedis = RedisPool.getJedis();
			if (jedis.setnx(getNameSpace(key), value).equals(1L)) {
				return true;
			}
		} catch (Exception e) {
			log.error("set key:{} value:{} error", getNameSpace(key), value, e);
			return false;
		} finally {
			RedisPool.close(jedis);
		}

		return false;
	}

	/**
	 * 获取
	 * 
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		Jedis jedis = null;
		String result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.get(getNameSpace(key));
		} catch (Exception e) {
			log.error("get key:{} error", getNameSpace(key), e);
			return result;
		} finally {
			RedisPool.close(jedis);
		}
		return result;
	}

	public static Object getObj(String key) {
		Jedis jedis = null;
		byte[] result = null;
		try {
			jedis = RedisPool.getJedis();
			result = jedis.get(getNameSpace(key).getBytes(default_charset));
		} catch (Exception e) {
			log.error("get key:{} error", getNameSpace(key), e);
			return result;
		} finally {
			RedisPool.close(jedis);
		}
		if (null != result) {
			return SerializeUtil.unserialize(result);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T get(String key, Class<?> clz) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			String value = jedis.get(getNameSpace(key));
			if (value != null && clz != null) {
				return (T) JSON.parseObject(value, clz);
				// return mapper.readValue(value, clz);
			} else {
				return (T) value;
			}
		} catch (Exception e) {
			log.error("get cache exception，key is ： " + key, e);
			return null;
		} finally {
			RedisPool.close(jedis);
		}
	}

	/**
	 * 删除
	 * 
	 * @param keys
	 * @return
	 */
	public static Long del(String... keys) {
		return del(true, keys);
	}

	/**
	 * 
	 * @param isJion 是否进行拼接
	 * @param keys
	 * @return
	 */
	public static Long del(boolean isJion, String... keys) {
		Jedis jedis = null;
		Long result = 0L;
		try {
			jedis = RedisPool.getJedis();
			for (String key : keys) {
				if (isJion) {
					result += jedis.del(getNameSpace(key));
				} else {
					result += jedis.del(key);
				}
			}

		} catch (Exception e) {
			log.error("del key:{} error", keys, e);
			return result;
		} finally {
			RedisPool.close(jedis);
		}
		return result;
	}

//	/**
//	 * 删除byte[] 对象
//	 * 
//	 * @param key
//	 * @return
//	 */
//	public static Long delObj(String key) {
//		return delObj(true, key);
//
//	}
//
//	/**
//	 * 删除byte[] 对象
//	 * 
//	 * @param isJion
//	 *            是否进行拼接
//	 * @param key
//	 * @return
//	 */
//	public static Long delObj(boolean isJion, String key) {
//		Jedis jedis = null;
//		Long result = 0L;
//		try {
//			jedis = RedisPool.getJedis();
//			if (isJion) {
//				return jedis.del(SerializeUtil.serialize(getKey(key)));
//			} else {
//				return jedis.del(SerializeUtil.serialize(key));
//			}
//
//		} catch (Exception e) {
//			log.error("del key:{} error", key, e);
//			return result;
//		} finally {
//			RedisPool.close(jedis);
//		}
//	}

	/**
	 * 递增 1
	 * 
	 * @param key 键
	 * @return
	 */
	public static long incr(String key) {
		return incr(key, 1L);
	}

	/**
	 * 递增
	 * 
	 * @param key 键
	 * @param by  要增加几(大于0)
	 * @return
	 */
	public static long incr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递增因子必须大于0");
		}
		Jedis jedis = null;
		Long result = null;
		try {
			jedis = RedisPool.getJedis();
			result = new BigDecimal(jedis.incrByFloat(getNameSpace(key), new BigDecimal(delta).doubleValue())).longValue();

		} catch (Exception e) {
			log.error("incr key:{} error", getNameSpace(key), e);
			return result;
		} finally {
			RedisPool.close(jedis);
		}
		return result;
	}

	/**
	 * 返回原始值，同时设置新的值
	 * 
	 * @param key
	 * @param val
	 * @return
	 */
	public static Object getSet(String key, String value) {

		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			return jedis.getSet(getNameSpace(key), value);
		} catch (Exception e) {
			log.error("getSet key:{},val:{} error", getNameSpace(key), value, e);
		} finally {
			RedisPool.close(jedis);
		}
		return null;
	}

	/**
	 * 判断值是否存在
	 * 
	 * @param key
	 * @return
	 */
	public static boolean exists(String key) {

		boolean b = false;
		if (StringUtils.isBlank(key)) {
			return b;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			return jedis.exists(getNameSpace(key));
		} catch (Exception e) {
			log.error("exists key:{} error", getNameSpace(key), e);
		} finally {
			RedisPool.close(jedis);
		}

		return b;
	}

	public  static String getNameSpace(String key) {
		return new StringBuffer(RedisPool.getCacheNameSpace()).append("_").append(key).toString();
	}

	public static Set<String> getKeys(String pattern) {
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			return jedis.keys(pattern);
		} catch (Exception e) {
			log.error("getKeys key:{} error", pattern, e);

			return null;
		} finally {
			RedisPool.close(jedis);
		}
	}

	public static class SerializeUtil {
		/**
		 * 序列化
		 * 
		 * @param object
		 * @return
		 */
		public static byte[] serialize(Object object) {
			ObjectOutputStream oos = null;
			ByteArrayOutputStream baos = null;
			try {
				// 序列化
				baos = new ByteArrayOutputStream();
				oos = new ObjectOutputStream(baos);
				oos.writeObject(object);
				byte[] bytes = baos.toByteArray();
				return bytes;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * 反序列化
		 * 
		 * @param bytes
		 * @return
		 */
		public static Object unserialize(byte[] bytes) {
			ByteArrayInputStream bais = null;
			try {
				// 反序列化
				bais = new ByteArrayInputStream(bytes);
				ObjectInputStream ois = new ObjectInputStream(bais);
				return ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	/**
	 * 缓存中存入map结构的数据
	 * 
	 * @param key
	 * @param map
	 * @param expireSeconds 值>0则设置失效时间(单位秒);值<=0,则没有失效时间
	 */
	public static void setHashMapString(String key, Map<String, String> map, int expireSeconds) {
		if (StringUtils.isBlank(key) || map.size() == 0) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			jedis.hmset(key, map);
			if (expireSeconds > 0) {
				jedis.expire(key, expireSeconds);
			}
		} catch (Exception e) {
			log.error("setHashMapString key:{} val:{}error", key, JSON.toJSONString(map), e);

		} finally {
			RedisPool.close(jedis);
		}
	}

	/**
	 * 获取缓存中Map结构中某个field对应的值
	 * 
	 * @param key
	 * @param field
	 */
	public static String hGet(String key, String field) {
		String fieldValue = null;
		if (StringUtils.isBlank(key) || StringUtils.isBlank(field)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			fieldValue = jedis.hget(key, field);
		} catch (Exception e) {
			log.error("hGet key:{} val:{}field", key, field, e);

		} finally {
			RedisPool.close(jedis);
		}
		return fieldValue;
	}

	/**
	 * 获取缓存对象
	 * 
	 * @param key
	 */
	public static Map<String, String> hGetAll(final String key) {
		Map<String, String> obj = null;
		if (StringUtils.isBlank(key)) {
			return obj;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			obj = jedis.hgetAll(key);
		} catch (Exception e) {
			log.error("hGetAll key:{} ", key, e);

		} finally {
			RedisPool.close(jedis);
		}
		return obj;
	}

	/**
	 * zset增加元素
	 * 
	 * @param key
	 * @param score
	 * @param obj
	 */
	public static void zAddObj(String key, double score, Object obj) {
		if (StringUtils.isBlank(key) || obj == null) {
			return;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			if (obj instanceof String) {
				jedis.zadd(getNameSpace(key), score, obj.toString());
			} else {
				jedis.zadd(getNameSpace(key), score, JSON.toJSONString(obj));
			}
		} catch (Exception e) {
			log.error("zadd exception，key is ： " + key, e);
		} finally {
			RedisPool.close(jedis);
		}
	}

	/**
	 * 获取zset分页数据
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @param clz
	 * @return
	 */
	public static List<?> zGetObj(String key, long start, long end, Class<?> clz) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			Set<String> value = jedis.zrevrange(getNameSpace(key), start, end);
			List<String> list = new ArrayList<String>(value);
			if (value != null && clz != null) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("zget exception，key is ： " + key, e);
			return null;
		} finally {
			RedisPool.close(jedis);
		}
	}

	/**
	 * 获取zget分页数据，根据分数区间
	 * 
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @param clz
	 * @return
	 */
	public static List<?> zGetObjByScore(final String key, double max, double min, int offset, int count, final Class<?> clz) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			Set<String> value = jedis.zrevrangeByScore(getNameSpace(key), max, min, offset, count);
			List<String> list = new ArrayList<String>(value);
			if (value != null && clz != null) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("zget by score exception，key is ： " + key, e);
			return null;
		} finally {
			RedisPool.close(jedis);
		}
	}

	/**
	 * 获取zget分页数据，根据分数区间
	 * 
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @param clz
	 * @return
	 */
	public static List<?> zGetObjByScore(String key, String max, String min, int offset, int count, final Class<?> clz) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			Set<String> value = jedis.zrevrangeByScore(getNameSpace(key), max, min, offset, count);
			List<String> list = new ArrayList<String>(value);
			if (value != null && clz != null) {
				return list;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("zget by score exception，key is ： " + key, e);
			return null;
		} finally {
			RedisPool.close(jedis);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T zGetObjByScore( String key,Long score, final Class<?> clz) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			Set<String> value = jedis.zrangeByScore(getNameSpace(key), score, score);
			List<String> list = new ArrayList<String>(value);
			if (value != null && list.isEmpty() ==false ) {
				if(clz !=null ) {
					return (T)JSONObject.parseObject(list.get(0), clz);
				}else {
					return (T)list.get(0);
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("zget by score exception，key is ： " + key, e);
			return null;
		} finally {
			RedisPool.close(jedis);
		}
	}

	public static long zCount(final String key) {
		if (StringUtils.isBlank(key)) {
			return 0l;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			Long count = jedis.zcount(getNameSpace(key), SCORE_MIN, SCORE_MAX);
			return count;
		} catch (Exception e) {
			log.error("zcount by score exception，key is ： " + key, e);
			return 0l;
		}
	}

	public static long zCountByScore(final String key, String min) {
		if (StringUtils.isBlank(key)) {
			return 0l;
		}
		if (StringUtils.isBlank(min)) {
			log.error("zcount by score ，min is necessary，otherwise return zero。key is ： " + key);
			return 0l;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			Long count = jedis.zcount(getNameSpace(key), "(" + min, SCORE_MAX);
			return count;
		} catch (Exception e) {
			log.error("zcount by score exception，key is ： " + key, e);
			return 0l;
		} finally {
			RedisPool.close(jedis);
		}
	}

	public static long zRem(final String key, String member) {
		if (StringUtils.isBlank(key)) {
			return 0l;
		}
		if (StringUtils.isBlank(member)) {
			log.error("zrem member ，member is necessary，otherwise return zero。member is ： " + member);
			return 0l;
		}
		Jedis jedis = null;
		try {
			jedis = RedisPool.getJedis();
			Long count = jedis.zrem(getNameSpace(key), member);
			return count;
		} catch (Exception e) {
			log.error("zRem by score exception，member is ： " + member, e);
			return 0l;
		}
	}

}
