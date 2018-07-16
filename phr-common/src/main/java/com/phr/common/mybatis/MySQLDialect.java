package com.phr.common.mybatis;

public class MySQLDialect extends Dialect{  
	
	private static String limitString =" limit ";
	private static String commaString =" , ";
	  
    public String getLimitString(String sql, int offset, int limit) {  
            return getLimitString(sql,offset,String.valueOf(offset),limit,String.valueOf(limit));  
    }  
      
    public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {  
//        if (offset > limit) {     
//            return sql + " limit "+offsetPlaceholder+","+limitPlaceholder;   
//        } else {     
//            return sql + " limit "+limitPlaceholder;  
//        }    
    	sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		pagingSelect.append(sql).append(limitString).append(offsetPlaceholder).append(commaString).append(limitPlaceholder);
		return pagingSelect.toString();
    }     
    
}  