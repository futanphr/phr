package com.phr.rest.remote.feign;

import com.phr.common.dto.ResultData;
import com.phr.rest.config.FeignConfig;
import feign.Headers;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "user", url = "${remote.user.url}",configuration = FeignConfig.class)
public interface UserClient {

    //feign独有的注解方式
    @Headers({"Content-Type: application/x-www-form-urlencoded","Accept: application/json"})
    @RequestLine("GET /user/getUser1")
    ResultData getUser();
}
