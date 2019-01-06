<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${nameSpace}">

	 <resultMap type="${resultType}" id="BaseResultMap">
    	 ${resultMap}  
    </resultMap>
    <sql id="Base_Column_List">
    	 ${baseColumnList}
    </sql>

    <insert id="insertSelective" parameterType="${resultType}">
	${insertSql}
    </insert>

    <select id="selectByPrimaryKey" resultMap="BaseResultMap"
		parameterType="java.lang.Long">
		${selectSql}
	</select>

    <update id="updateByPrimaryKeySelective" parameterType="${resultType}">
	${updateSql}
    </update>

	<delete id="deleteByPrimaryKey" parameterType="java.lang.Long">
		${deleteSql}
	</delete>
	
	<select id="getEntityByParams" resultMap="BaseResultMap"
        parameterType="java.util.Map">
	${listSql}
    </select>

    <select id="getListByParams" resultMap="BaseResultMap"
        parameterType="java.util.Map">
	${listSql}
    </select>

    <select id="getEntityByKeys" resultMap="BaseResultMap"
        parameterType="${resultType}">
	${listSql}
    </select>

    <select id="getListByKeys" resultMap="BaseResultMap"
        parameterType="${resultType}">
	${listSql}
    </select>

	<select id="getListByPage" resultMap="BaseResultMap"
		parameterType="java.util.Map">
		${listSql}
	</select>

	<select id="getListCount" resultType="java.lang.Integer"
		parameterType="java.util.Map">
		${listCountSql}
	</select>
</mapper>