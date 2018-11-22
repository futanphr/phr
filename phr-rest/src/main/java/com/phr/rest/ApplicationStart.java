package com.phr.rest;

import com.phr.rest.config.RedisConfig;
import com.phr.rest.mybatis.shardingjdbc.config.DataSourceConfig;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = { "com.phr.*" }, includeFilters = @ComponentScan.Filter(classes = org.springframework.stereotype.Controller.class, type = FilterType.ANNOTATION))
@Import({RedisConfig.class,DataSourceConfig.class})
@EnableCaching //开启缓存
@EnableFeignClients//开启feign
public class ApplicationStart {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(ApplicationStart.class, args);
	}
}
