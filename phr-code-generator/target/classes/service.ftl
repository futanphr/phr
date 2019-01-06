<#--<#include "/java_copyright.include"> -->
package ${basepackage}.${mpackage}.service;

<#--<#include "/java_imports.include">  -->
import ${basepackage}.${mpackage}.entity.${className}Entity;
import java.util.Map;
import java.util.List;
import com.github.pagehelper.PageInfo;
import java.util.Optional;
/**
 *
 * @time ${createTime}
 * @version 1.0
 * @description 与数据库中表${tableName}相应操作的接口
 **/

public interface ${className}Service  {

	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	int insertSelective(${className}Entity record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	Optional<${className}Entity> selectByPrimaryKey(Long id);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(${className}Entity record);
  	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);
	/**
	 * 通过map参数 获取实体对象
	 * @return
	 */
	Optional<${className}Entity> getEntityByParams(Map<String,Object> params);
	/**
	 * 通过map参数获取列表
	 * @return List<${className}Entity>
    */
   Optional<List<${className}Entity>> getListByParams(Map<String,Object> params);
	/**
	 * 通过对象中的某些字段 获取实体对象
	 * @return
	 */
    Optional<${className}Entity> getEntityByKeys(${className}Entity entity);
    /**
    * 通过对象中的某些字段获取列表
    * @return List<${className}Entity>
	*/
    Optional<List<${className}Entity>> getListByKeys(${className}Entity entity);
	/**
	 * 通过map参数获取列表 分页
	 */
    PageInfo<${className}Entity> getListByPage(Map<String,Object> params,Integer pageNo,Integer pageSize,Integer navigatePages);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

}