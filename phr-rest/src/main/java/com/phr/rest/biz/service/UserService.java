package com.phr.rest.biz.service;

import com.phr.core.entity.PageInfo;
import com.phr.rest.biz.entity.UserEntity;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;
/**
 *
 * @time 2018年07月13日 14:56:53
 * @version 1.0
 *
 **/

public interface UserService  {
  	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);
	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	int insertSelective(UserEntity record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	UserEntity selectByPrimaryKey(Long id);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(UserEntity record);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<UserEntity>
	 */
	List<UserEntity> getList(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return PageInfo<UserEntity>
	 */
	PageInfo<UserEntity> getList(PageInfo<UserEntity> pageInfo,Map<String,Object> params);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);
	public String getUser1(int id);

	public String getUser2(int id);
}