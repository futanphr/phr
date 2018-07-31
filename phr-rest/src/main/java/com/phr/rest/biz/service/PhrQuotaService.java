package com.phr.rest.biz.service;

import com.phr.rest.biz.entity.PhrQuotaEntity;
import java.util.Map;
import java.util.List;
import com.phr.core.entity.PageInfo;
/**
 *
 * @time 2018年07月31日 10:16:32
 * @version 1.0
 *
 **/

public interface PhrQuotaService  {
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
	int insertSelective(PhrQuotaEntity record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	PhrQuotaEntity selectByPrimaryKey(Long id);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(PhrQuotaEntity record);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<PhrQuotaEntity>
	 */
	List<PhrQuotaEntity> getList(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return PageInfo<PhrQuotaEntity>
	 */
	PageInfo<PhrQuotaEntity> getListByPage(Map<String,Object> params,Integer currentPage,Integer pageSize);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

}