package com.phr.rest.biz.mapper;

import com.phr.rest.biz.entity.PhrOrderEntity;
import java.util.Map;
import java.util.List;
import java.util.Optional;
//import org.apache.ibatis.session.RowBounds;
/**
 *
 * @time 2019年01月07日 10:27:57
 * @version 1.0
 *
 **/

public interface PhrOrderMapper  {
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
    * 通过对象中的某些字段获取列表
    * @return List<PhrOrderEntity>
    */
	List<PhrOrderEntity> getListByKeys(PhrOrderEntity entity);
    /**
    * 通过map参数获取列表
    * @param params
    * @return List<PhrOrderEntity>
    */
    List<PhrOrderEntity> getListByParams(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return List<PhrOrderEntity>
	 */
    List<PhrOrderEntity> getListByPage(Map<String,Object> params);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

}