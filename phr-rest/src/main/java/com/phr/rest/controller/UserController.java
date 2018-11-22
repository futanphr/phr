package com.phr.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phr.common.dto.ResultData;
import com.phr.rest.biz.entity.UserEntity;
import com.phr.rest.biz.service.UserService;
import com.phr.rest.dto.request.RequestUserEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value = "测试Swagger2",description="简单的API")
public class UserController {
	@Autowired
	private UserService userService;
	Logger logger=LoggerFactory.getLogger(UserController.class);
	
	
	@ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public ResultData index(RequestUserEntity requestUserEntity) {
		ResultData rs=new ResultData();
		UserEntity userEntity=new UserEntity();
		userEntity.setAge(requestUserEntity.getAge());
//		userEntity.setBirth(requestUserEntity.getBirth());
		userEntity.setCityId(requestUserEntity.getCityId());
		userEntity.setUserId(requestUserEntity.getUserId());
		userEntity.setUserName(requestUserEntity.getUserName());
		userService.insertSelective(userEntity);
		Map map=new HashMap<String,Object>();
		rs.setData(map);
		logger.info("打印日志");
		return rs;
	}
	
	@RequestMapping(value = "/getUser1" ,method = RequestMethod.GET)
    public ResultData getUser1() {
        return userService.getUser1(003);
    }
	/*@GetMapping("/getUser2")
    public String getUse1r() {
        return userService.getUser2(005);
    }*/
}
