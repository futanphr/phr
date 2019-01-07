<#--<#include "/java_copyright.include"> -->
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${mpackage}.entity;

<#--<#include "/java_imports.include">  -->
<#--import com.snailf.platforms.common.entity.BaseEntity;-->
 import java.io.Serializable; 
<#if import>
import java.util.Date;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
/**
<#if table.entityRemark??>
 *
 * @类介绍 ${table.entityRemark}
 </#if>
 * @time ${table.createTime}
 * @version 1.0
 * @description 数据库表对应的实体类，类中属性与数据库字段对应
 **/

@SuppressWarnings("serial")
public class ${className}Entity implements Serializable  {
    <#list table.columns as column>
    <#if column.remarks ??>
    /**
     * ${column.remarks}
     */
    </#if>
    private ${column.simpleJavaType} ${column.columnNameLower};
    </#list>
<@generateJavaColumns/>

<#macro generateJavaColumns>
    <#list table.columns as column>
        <#if column.isDateTimeColumn>
    public String get${column.columnName}String() {
        return DateConvertUtils.format(get${column.columnName}(), FORMAT_${column.constantName});
    }
    public void set${column.columnName}String(String ${column.columnNameLower}) {
        set${column.columnName}(DateConvertUtils.parse(${column.columnNameLower}, FORMAT_${column.constantName},${column.simpleJavaType}.class));
    }
        </#if>  
       <#if (column.remarks) ??>
	/**
	 * 设置${column.remarks}
	 */
	    </#if>
    public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }
     <#if (column.remarks) ??>
    /**
     * 获取${column.remarks}
     */
    </#if>
    public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }
    </#list>

    /**
     * 以下代码是采用设计模式中的建造者模式，链式编程
     * 例如创建一个对象，并且给对象的id赋值100，
     * 我们可以这么写：${className}Entity entity=new ${className}Entity.Builder.setId(100).build();
     */
    public static class Builder{
    <#list table.columns as column>
    private ${column.simpleJavaType} ${column.columnNameLower}=null;
    </#list>

    <#list table.columns as column>
    public Builder set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
        return this;
    }
    </#list>

    public ${className}Entity build(){
        return new ${className}Entity(this);
    }
    }

    public ${className}Entity(Builder builder){
    <#list table.columns as column>
      this.${column.columnNameLower} = builder.${column.columnNameLower};
    </#list>
    }
    /**
     * 默认构造函数，不要省掉这行代码
     */
    public ${className}Entity(){}

</#macro>
}