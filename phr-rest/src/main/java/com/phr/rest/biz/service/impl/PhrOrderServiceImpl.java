package com.phr.rest.biz.service.impl;

import com.phr.rest.biz.entity.PhrOrderEntity;
import com.phr.rest.biz.service.PhrOrderService;
import com.phr.rest.biz.mapper.PhrOrderMapper;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.*;

/**
 *
 * @time 2019年01月07日 10:27:58
 * @version 1.0
 * @description 与数据库中表t_phr_order相应操作的实现
 **/
@Service("phrOrderService")
public class PhrOrderServiceImpl  implements PhrOrderService{

	@Autowired
	private PhrOrderMapper phrOrderMapper;
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
	public Optional<PhrOrderEntity> selectByPrimaryKey(Long id){
		return phrOrderMapper.selectByPrimaryKey(id);
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
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int deleteByPrimaryKey(Long id){
		return	phrOrderMapper.deleteByPrimaryKey(id);
	}
    /**
    * 通过对象中的某些字段获取列表
    * @return List<PhrOrderEntity>
	*/
	public Optional<List<PhrOrderEntity>> getListByKeys(PhrOrderEntity entity){
	    return  Optional.ofNullable(phrOrderMapper.getListByKeys(entity));
	}
	/**
	* 通过对象中的某些字段 获取实体对象
	* @return
	*/
	public Optional<PhrOrderEntity> getEntityByKeys(PhrOrderEntity entity){
		return phrOrderMapper.getListByKeys(entity).stream().max(Comparator.comparing(PhrOrderEntity::getId));
	}
	/**
	* 通过map参数获取列表
	* @param params
	* @return List<PhrOrderEntity>
	*/
	public Optional<List<PhrOrderEntity>> getListByParams(Map<String,Object> params){
	   return  Optional.ofNullable(phrOrderMapper.getListByParams(params));
	}
	/**
	 * 通过map 获取实体对象
	 * @return
	 */
	public Optional<PhrOrderEntity> getEntityByParams(Map<String,Object> params){
		return phrOrderMapper.getListByParams(params).stream().max(Comparator.comparing(PhrOrderEntity::getId));
	}
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return PageInfo<PhrOrderEntity>
	 */

	public PageInfo<PhrOrderEntity> getListByPage(Map<String,Object> params,Integer pageNo,Integer pageSize,Integer navigatePages){
        pageNo =( pageNo == null || pageNo == 0 ) ? 1 : pageNo;
        pageSize = ( pageSize == null || pageSize == 0 ) ? 10 : pageSize;
        navigatePages=( navigatePages == null || navigatePages == 0 ) ? 10 : navigatePages;
		PageHelper.startPage(pageNo,pageSize);
		List<PhrOrderEntity> list = phrOrderMapper.getListByParams(params);
		PageInfo<PhrOrderEntity> pageInfo = new PageInfo<PhrOrderEntity>(list, navigatePages);
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