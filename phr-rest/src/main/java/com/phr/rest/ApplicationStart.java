package com.phr.rest;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan.Filter;

import com.phr.common.redis.SpringRedisCacheManager;
import com.phr.rest.biz.config.RedisConfig;
import com.phr.rest.config.ApplicationConfig;
import com.phr.rest.mybatis.shardingjdbc.config.DataSourceConfig;
import com.phr.rest.mybiz.CountNumber;


public class ApplicationStart {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(ApplicationConfig.class, args);
		/* ConfigurableApplicationContext context = SpringApplication.run(ApplicationConfig.class, args);

	        @Async和@EnableAsync配合使用
	    context.getBean(CountNumber.class).PrintNumber();
	        for(int i=1; i<10; i++){
	            TimeUnit.MICROSECONDS.sleep(1);
	            System.out.println("------------------");
	        }*/
	}
}
