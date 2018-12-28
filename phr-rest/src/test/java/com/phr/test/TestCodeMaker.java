package com.phr.test;

import com.snailf.config.CodeGenerator;
import com.snailf.config.CodeGeneratorLinux;


public class TestCodeMaker {

	// 数据库地址
	private static String ip = "localhost";
	// 数据库端口号
	private static String port = "3306";
	// 数据库用户名
	private static String userName = "root";
	// 数据库密码
	private static String password = "root123";
	
	// 数据库名称
	private static String dbName = "springboot";
	// 要生成代码的表名
	private static String tableName = "t_phr_order";
	// 基础包名 windows写法
	private static String basePackage = "com.phr.rest";
	// 业务包名
	private static String servicePackage = "biz";

	// 项目名称
	// private static String projectName = "helloword";
	// private static String projectName = "code-generator";

	public static void main(String[] args) {
		CodeGeneratorLinux code = new CodeGeneratorLinux(ip, port, userName, password, dbName, tableName, basePackage,
				servicePackage);
		code.run();
		
	
		

	}
}
