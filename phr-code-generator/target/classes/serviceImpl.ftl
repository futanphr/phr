<#--<#include "/java_copyright.include"> -->
package ${basepackage}.${mpackage}.service.impl;

<#--<#include "/java_imports.include">  -->
import ${basepackage}.${mpackage}.entity.${className}Entity;
import ${basepackage}.${mpackage}.service.${className}Service;
import ${basepackage}.${mpackage}.mapper.${className}Mapper;
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
 * @time ${createTime}
 * @version 1.0
 * @description 与数据库中表${tableName}相应操作的实现
 **/
@Service("${firsetLowerClassName}Service")
public class ${className}ServiceImpl  implements ${className}Service{

	@Autowired
	private ${className}Mapper ${firsetLowerClassName}Mapper;
	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int insertSelective(${className}Entity record){
		return ${firsetLowerClassName}Mapper.insertSelective(record);
	}
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	public Optional<${className}Entity> selectByPrimaryKey(Long id){
		return ${firsetLowerClassName}Mapper.selectByPrimaryKey(id);
	}
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int updateByPrimaryKeySelective(${className}Entity record){
		return ${firsetLowerClassName}Mapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int deleteByPrimaryKey(Long id){
		return	${firsetLowerClassName}Mapper.deleteByPrimaryKey(id);
	}
    /**
    * 通过对象中的某些字段获取列表
    * @return List<${className}Entity>
	*/
	public Optional<List<${className}Entity>> getListByKeys(${className}Entity entity){
	    return  Optional.ofNullable(${firsetLowerClassName}Mapper.getListByKeys(entity));
	}
	/**
	* 通过对象中的某些字段 获取实体对象
	* @return
	*/
	public Optional<${className}Entity> getEntityByKeys(${className}Entity entity){
		return ${firsetLowerClassName}Mapper.getListByKeys(entity).stream().max(Comparator.comparing(${className}Entity::getId));
	}
	/**
	* 通过map参数获取列表
	* @param params
	* @return List<${className}Entity>
	*/
	public Optional<List<${className}Entity>> getListByParams(Map<String,Object> params){
	   return  Optional.ofNullable(${firsetLowerClassName}Mapper.getListByParams(params));
	}
	/**
	 * 通过map 获取实体对象
	 * @return
	 */
	public Optional<${className}Entity> getEntityByParams(Map<String,Object> params){
		return ${firsetLowerClassName}Mapper.getListByParams(params).stream().max(Comparator.comparing(${className}Entity::getId));
	}
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return PageInfo<${className}Entity>
	 */

	public PageInfo<${className}Entity> getListByPage(Map<String,Object> params,Integer pageNo,Integer pageSize,Integer navigatePages){
        pageNo =( pageNo == null || pageNo == 0 ) ? 1 : pageNo;
        pageSize = ( pageSize == null || pageSize == 0 ) ? 10 : pageSize;
        navigatePages=( navigatePages == null || navigatePages == 0 ) ? 10 : navigatePages;
		PageHelper.startPage(pageNo,pageSize);
		List<${className}Entity> list = ${firsetLowerClassName}Mapper.getListByParams(params);
		PageInfo<${className}Entity> pageInfo = new PageInfo<${className}Entity>(list, navigatePages);
		return pageInfo;
	}
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	public int getListCount(Map<String,Object> params){
		return  ${firsetLowerClassName}Mapper.getListCount(params);
	}

}