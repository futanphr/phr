package com.phr.rest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;

import com.phr.common.redis.SpringRedisCacheManager;
import com.phr.rest.biz.config.RedisConfig;
import com.phr.rest.config.ApplicationConfig;
import com.phr.rest.mybatis.shardingjdbc.config.DataSourceConfig;


public class ApplicationStart {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfig.class, args);
	}
}
