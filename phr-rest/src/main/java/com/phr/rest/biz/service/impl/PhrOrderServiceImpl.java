package com.phr.rest.biz.service.impl;

import com.phr.core.entity.PageInfoBak;
import com.phr.rest.biz.entity.PhrOrderEntity;
import com.phr.rest.biz.service.PhrOrderService;
import com.phr.rest.biz.mapper.PhrOrderMapper;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.ibatis.session.RowBounds;
/**
 *
 * @time 2018年07月13日 14:55:07
 * @version 1.0
 *
 **/
@Service("phrOrderService")
public class PhrOrderServiceImpl  implements PhrOrderService{

	@Autowired
	private PhrOrderMapper phrOrderMapper;
  	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int deleteByPrimaryKey(Long id){
		return	phrOrderMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int insertSelective(PhrOrderEntity record){
		return phrOrderMapper.insertSelective(record);
	}
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	public PhrOrderEntity selectByPrimaryKey(Long id){
		return phrOrderMapper.selectByPrimaryKey(id);
	}
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	@Cacheable(value = "my-redis-cache2",key = "#orderId")
	public PhrOrderEntity selectByOrderId(Long orderId){
		System.out.println("===>测试此方法是否执行");
		return phrOrderMapper.selectByOrderId(orderId);
	}
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int updateByPrimaryKeySelective(PhrOrderEntity record){
		return phrOrderMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<PhrOrderEntity>
	 */
	public List<PhrOrderEntity> getList(Map<String,Object> params){
		return  phrOrderMapper.getList(params);
	}
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return PageInfo<PhrOrderEntity>
	 */
	public PageInfoBak<PhrOrderEntity> getList(PageInfoBak<PhrOrderEntity> pageInfo,Map<String,Object> params){
		List<PhrOrderEntity> list = phrOrderMapper.getList(params,
				new RowBounds(pageInfo.getStart(), pageInfo.getPageSize()));
		Integer total = phrOrderMapper.getListCount(params);
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
		return  phrOrderMapper.getListCount(params);
	}

}