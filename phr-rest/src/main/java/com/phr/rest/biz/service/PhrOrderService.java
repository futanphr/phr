package com.phr.rest.biz.service;

import com.phr.rest.biz.entity.PhrOrderEntity;
import java.util.Map;
import java.util.List;
import com.github.pagehelper.PageInfo;
import java.util.Optional;
/**
 *
 * @time 2019年01月06日 23:11:47
 * @version 1.0
 * @description 与数据库中表t_phr_order相应操作的接口
 **/

public interface PhrOrderService  {

	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	int insertSelective(PhrOrderEntity record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	Optional<PhrOrderEntity> selectByPrimaryKey(Long id);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(PhrOrderEntity record);
  	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);
	/**
	 * 通过map参数 获取实体对象
	 * @return
	 */
	Optional<PhrOrderEntity> getEntityByParams(Map<String,Object> params);
	/**
	 * 通过map参数获取列表
	 * @return List<PhrOrderEntity>
    */
   Optional<List<PhrOrderEntity>> getListByParams(Map<String,Object> params);
	/**
	 * 通过对象中的某些字段 获取实体对象
	 * @return
	 */
    Optional<PhrOrderEntity> getEntityByKeys(PhrOrderEntity entity);
    /**
    * 通过对象中的某些字段获取列表
    * @return List<PhrOrderEntity>
	*/
    Optional<List<PhrOrderEntity>> getListByKeys(PhrOrderEntity entity);
	/**
	 * 通过map参数获取列表 分页
	 */
    PageInfo<PhrOrderEntity> getListByPage(Map<String,Object> params,Integer pageNo,Integer pageSize,Integer navigatePages);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

}