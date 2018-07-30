package com.jfcf.quota.service.impl;

import com.jfcf.quota.entity.QuotaChannelMessageEntity;
import com.jfcf.quota.service.QuotaChannelMessageService;
import com.jfcf.quota.mapper.QuotaChannelMessageMapper;
import java.util.Map;
import java.util.List;
import com.phr.core.entity.PageInfoBak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
//import org.apache.ibatis.session.RowBounds;
/**
 *
 * @time 2018年07月30日 15:19:46
 * @version 1.0
 *
 **/
@Service("quotaChannelMessageService")
public class QuotaChannelMessageServiceImpl  implements QuotaChannelMessageService{

	@Autowired
	private QuotaChannelMessageMapper quotaChannelMessageMapper;
  	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int deleteByPrimaryKey(Long id){
		return	quotaChannelMessageMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int insertSelective(QuotaChannelMessageEntity record){
		return quotaChannelMessageMapper.insertSelective(record);
	}
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	public QuotaChannelMessageEntity selectByPrimaryKey(Long id){
		return quotaChannelMessageMapper.selectByPrimaryKey(id);
	}
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int updateByPrimaryKeySelective(QuotaChannelMessageEntity record){
		return quotaChannelMessageMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<QuotaChannelMessageEntity>
	 */
	public List<QuotaChannelMessageEntity> getList(Map<String,Object> params){
		return  quotaChannelMessageMapper.getList(params);
	}
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return PageInfo<QuotaChannelMessageEntity>
	 */
	public PageInfoBak<QuotaChannelMessageEntity> getList(PageInfoBak<QuotaChannelMessageEntity> pageInfo,Map<String,Object> params){
		List<QuotaChannelMessageEntity> list = quotaChannelMessageMapper.getList(params,
				pageInfo.getStart(), pageInfo.getPageSize());
		Integer total = quotaChannelMessageMapper.getListCount(params);
		pageInfo.setRows(list);
		pageInfo.setTotal(total);
		return pageInfo;
	}
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	public int getListCount(Map<String,Object> params){
		return  quotaChannelMessageMapper.getListCount(params);
	}

}