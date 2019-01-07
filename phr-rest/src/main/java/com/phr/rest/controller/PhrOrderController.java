package com.phr.rest.controller;

import com.alibaba.fastjson.JSON;
import com.phr.common.dto.Result;
import com.phr.rest.biz.entity.PhrOrderEntity;
import com.phr.rest.biz.service.PhrOrderService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 控制层
 *
 * @author
 * @create 2019-01-06 下午11:33
 **/
@RestController
@RequestMapping("/server/api")
@Slf4j
public class PhrOrderController {

    @Resource
    PhrOrderService phrOrderService;

    @GetMapping("/order/info")
    public Result getOrderInfo(){
        PhrOrderEntity entity=  phrOrderService.getEntityByKeys(new PhrOrderEntity.Builder().setOrderId(20L).build()).orElse(null);
        log.info(JSON.toJSONString(entity));
        return new Result(entity);

    }
}
