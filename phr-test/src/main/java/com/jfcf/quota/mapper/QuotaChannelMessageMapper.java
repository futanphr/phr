package com.jfcf.quota.mapper;

import com.jfcf.quota.entity.QuotaChannelMessageEntity;
import java.util.Map;
import java.util.List;
//import org.apache.ibatis.session.RowBounds;
/**
 *
 * @time 2018年07月30日 15:19:46
 * @version 1.0
 *
 **/

public interface QuotaChannelMessageMapper  {
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
	int insertSelective(QuotaChannelMessageEntity record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	QuotaChannelMessageEntity selectByPrimaryKey(Long id);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(QuotaChannelMessageEntity record);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<QuotaChannelMessageEntity>
	 */
	List<QuotaChannelMessageEntity> getList(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return List<QuotaChannelMessageEntity>
	 */
	List<QuotaChannelMessageEntity> getList(Map<String,Object> params,int pageNum,int pageSize);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

}