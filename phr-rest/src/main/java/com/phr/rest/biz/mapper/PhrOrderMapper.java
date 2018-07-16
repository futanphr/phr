package com.phr.rest.biz.mapper;

import com.phr.rest.biz.entity.PhrOrderEntity;
import java.util.Map;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
/**
 *
 * @time 2018年07月13日 14:55:07
 * @version 1.0
 *
 **/

public interface PhrOrderMapper  {
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
	int insertSelective(PhrOrderEntity record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	PhrOrderEntity selectByPrimaryKey(Long id);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	PhrOrderEntity selectByOrderId(Long OrderId);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(PhrOrderEntity record);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<PhrOrderEntity>
	 */
	List<PhrOrderEntity> getList(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return List<PhrOrderEntity>
	 */
	List<PhrOrderEntity> getList(Map<String,Object> params,RowBounds rowBounds);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

}