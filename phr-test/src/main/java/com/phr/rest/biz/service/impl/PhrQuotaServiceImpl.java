package com.phr.rest.biz.service.impl;

import com.phr.rest.biz.entity.PhrQuotaEntity;
import com.phr.rest.biz.service.PhrQuotaService;
import com.phr.rest.biz.mapper.PhrQuotaMapper;
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
 * @time 2018年07月30日 15:32:39
 * @version 1.0
 *
 **/
@Service("phrQuotaService")
public class PhrQuotaServiceImpl  implements PhrQuotaService{

	@Autowired
	private PhrQuotaMapper phrQuotaMapper;
  	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int deleteByPrimaryKey(Long id){
		return	phrQuotaMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int insertSelective(PhrQuotaEntity record){
		return phrQuotaMapper.insertSelective(record);
	}
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	public PhrQuotaEntity selectByPrimaryKey(Long id){
		return phrQuotaMapper.selectByPrimaryKey(id);
	}
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int updateByPrimaryKeySelective(PhrQuotaEntity record){
		return phrQuotaMapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<PhrQuotaEntity>
	 */
	public List<PhrQuotaEntity> getList(Map<String,Object> params){
		return  phrQuotaMapper.getList(params);
	}
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return PageInfo<PhrQuotaEntity>
	 */
	public PageInfoBak<PhrQuotaEntity> getList(PageInfoBak<PhrQuotaEntity> pageInfo,Map<String,Object> params){
		List<PhrQuotaEntity> list = phrQuotaMapper.getList(params,
				pageInfo.getStart(), pageInfo.getPageSize());
		Integer total = phrQuotaMapper.getListCount(params);
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
		return  phrQuotaMapper.getListCount(params);
	}

}