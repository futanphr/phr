package com.phr.rest.biz.service.impl;

import com.phr.common.dto.ResultData;
import com.phr.core.entity.PageInfoBak;
import com.phr.rest.biz.entity.UserEntity;
import com.phr.rest.biz.service.UserService;
import com.phr.rest.biz.mapper.UserMapper;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.ibatis.session.RowBounds;

/**
 *
 * @time 2018年07月13日 14:56:53
 * @version 1.0
 *
 **/
@Service("userService")
public class UserServiceImpl implements UserService {
	/*@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	RedisTemplate redisTemplate;*/

	@Autowired
	private UserMapper userMapper;

	/**
	 * 通过主键id 删除
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int deleteByPrimaryKey(Long id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 插入实体
	 * 
	 * @param record
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int insertSelective(UserEntity record) {
		return userMapper.insertSelective(record);
	}

	/**
	 * 通过主键id 获取实体对象
	 * 
	 * @param id
	 * @return
	 */
	public UserEntity selectByPrimaryKey(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * 通过主键id 更新实体
	 * 
	 * @param record
	 * @return 1成功 其它失败
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int updateByPrimaryKeySelective(UserEntity record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 通过map参数获取列表
	 * 
	 * @param params
	 * @return List<UserEntity>
	 */
	public List<UserEntity> getList(Map<String, Object> params) {
		return userMapper.getList(params);
	}

	/**
	 * 通过map参数获取列表 分页
	 * 
	 * @param params
	 * @return PageInfo<UserEntity>
	 */
	public PageInfoBak<UserEntity> getList(PageInfoBak<UserEntity> pageInfo, Map<String, Object> params) {
		List<UserEntity> list = userMapper.getList(params, new RowBounds(pageInfo.getStart(), pageInfo.getPageSize()));
		Integer total = userMapper.getListCount(params);
		pageInfo.setRows(list);
		pageInfo.setTotal(total);
		return pageInfo;
	}

	/**
	 * 通过map参数获取 总数
	 * 
	 * @param params
	 * @return int
	 */
	public int getListCount(Map<String, Object> params) {
		return userMapper.getListCount(params);
	}

	//@Cacheable(value = "my-redis-cache1", key = "#id", unless = "#result==null")
	public ResultData getUser1(int id) {
		System.out.println("i am from userService getUser1");
		//System.out.println(stringRedisTemplate.getValueSerializer());
		//System.out.println(redisTemplate.getValueSerializer());
		ResultData rs=new ResultData();
		rs.setData("fee");
		return rs;
	}

	/*@Cacheable(value = "my-redis-cache2", key = "#id", unless = "#result==null")
	public String getUser2(int id) {
		System.out.println("i am from userService getUser2");
		System.out.println(stringRedisTemplate.getValueSerializer());
		System.out.println(redisTemplate.getValueSerializer());
		return "fee";
	}*/
}