package com.phr.rest.biz.service.impl;

import com.phr.rest.biz.entity.PhrOrderEntity;
import com.phr.rest.biz.service.PhrOrderService;
import com.phr.rest.biz.mapper.PhrOrderMapper;
import java.util.Map;
import java.util.List;
import com.phr.core.entity.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
//import org.apache.ibatis.session.RowBounds;
/**
 *
 * @time 2018年12月28日 17:14:13
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
	 * 通过map 获取实体对象
	 * @param id
	 * @return
	 */
	public PhrOrderEntity selectByKeys(Map<String,Object> params){
		return phrOrderMapper.selectByKeys(params);
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

	public PageInfo<PhrOrderEntity> getListByPage(Map<String,Object> params,Integer currentPage,Integer pageSize){
	    currentPage =( currentPage == null || currentPage == 0 ) ? 1 : currentPage;
        pageSize = ( pageSize == null || pageSize == 0 ) ? 10 : pageSize;
		PageHelper.startPage(currentPage,pageSize);
		List<PhrOrderEntity> list = phrOrderMapper.getList(params);
		Integer total = phrOrderMapper.getListCount(params);
		PageInfo<PhrOrderEntity> pageInfo = new PageInfo<PhrOrderEntity>(currentPage, pageSize, total);
		pageInfo.setItems(list);
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