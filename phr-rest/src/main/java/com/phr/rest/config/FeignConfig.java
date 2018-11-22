package com.phr.rest.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.*;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @author madenghui
 * spring-cloud-openfeign
 */
@Configuration
@Slf4j
public class FeignConfig {

    /**
     * 有关hystrix相关的配置见app.yml
     * @return
     */
    @Bean
    public Feign.Builder hystrixBuilder(){
        return HystrixFeign.builder();
    }

    /**
     * 配置使用peign原生协议和注解
     * @return
     */
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }

//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("user", "password");
//    }


    @Bean
    public Encoder feignEncoder(){
        return new FormEncoder(new JacksonEncoder());
    }

    @Bean
    public Decoder feignDecoder(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return new JacksonDecoder(objectMapper);
    }

    @Bean
    public Request.Options feignOptions(){
        return new Request.Options(5000, 15000);
    }

    @Bean
    public Retryer feignRetryer(){
//        return new Retryer.Default(5000, 5000, 0);
        return Retryer.NEVER_RETRY;
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new ErrorDecoder() {
            @Override
            public Exception decode(String s, Response response) {
                log.info("==================================");
                log.info("feign error {}", s);
                return null;
            }
        };
    }
}
