package com.phr.swagger.test;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public User getUserInfo() {
		User user=new User();
		user.setUsername("zhangsan");
		return user;
	}
	
	

}
