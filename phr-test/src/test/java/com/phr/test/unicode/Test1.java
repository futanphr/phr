package com.phr.test.unicode;

public class Test1 {
	public static void main(String[] args) {
		String s="aa@bb@cc@dd@ff";
		String[] ss=s.split("@");
		for(String str:ss) {
			System.out.println(str);
			System.out.println("本地代码");

			System.out.println("远程代码1");
			

			System.out.println("远程代码3");


		}
	}

}
