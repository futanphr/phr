package com.phr.rest.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
* Redis缓存配置类
* @author penghuari
*
*/
//@Configuration
//@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

   //缓存管理器
 /*  @Bean
   public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
       //user信息缓存配置
       RedisCacheConfiguration userCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(100)).disableCachingNullValues().prefixKeysWith("user");
       Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();
       redisCacheConfigurationMap.put("user", userCacheConfiguration);
       //初始化一个RedisCacheWriter
       RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);


//       设置CacheManager的值序列化方式为JdkSerializationRedisSerializer,但其实RedisCacheConfiguration默认就是使用StringRedisSerializer序列化key，JdkSerializationRedisSerializer序列化value,所以以下注释代码为默认实现
//       ClassLoader loader = this.getClass().getClassLoader();
//       JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer(loader);
//       RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jdkSerializer);
//       RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);

       RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration.defaultCacheConfig();

       //设置默认超过期时间是30秒
       defaultCacheConfig.entryTtl(Duration.ofSeconds(30));
       //初始化RedisCacheManager
       RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, defaultCacheConfig, redisCacheConfigurationMap);
       return cacheManager;
   }
*/
	@Bean(name = "cacheManager") 
	public CacheManager cacheManager(RedisConnectionFactory factory) {
	    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();  // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
	    config = config.entryTtl(Duration.ofMinutes(1)) ;    // 设置缓存的默认过期时间，也是使用Duration设置
//	            .disableCachingNullValues();     // 不缓存空值

	    // 设置一个初始化的缓存空间set集合
	    Set<String> cacheNames =  new HashSet<>();
	    cacheNames.add("my-redis-cache1");
	    cacheNames.add("my-redis-cache2");

	    // 对每个缓存空间应用不同的配置
	    Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
	    configMap.put("my-redis-cache1", config);
	    configMap.put("my-redis-cache2", config.entryTtl(Duration.ofSeconds(120)));

	    RedisCacheManager cacheManager = RedisCacheManager.builder(factory)     // 使用自定义的缓存配置初始化一个cacheManager
	            .initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
	            .withInitialCacheConfigurations(configMap)
	            .build();
	    return cacheManager;
	}

}
