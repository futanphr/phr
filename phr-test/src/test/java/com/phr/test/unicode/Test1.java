package com.phr.test.unicode;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class Test1 {
	public static void main(String[] args) {
		String s="aa@bb@cc@dd@ff";
		String[] ss=s.split("@");
		for(String str:ss) {
			System.out.println(str);
			System.out.println("本地代码");

			System.out.println("远程代码1");
			

			System.out.println("远程代码31");
			System.out.println("远程代码2");
			
			
			


		}
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		Date date1 = calendar.getTime();
		System.out.println(date1.getTime());
		System.out.println((int)date1.getTime());
		System.out.println(sdf.format(date1));
		
		String str = "a,b,c";  
		List<String> result = Arrays.asList(StringUtils.split(str,","));  
		System.out.println(result);

	}

}
