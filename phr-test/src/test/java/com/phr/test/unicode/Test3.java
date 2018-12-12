package com.phr.test.unicode;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Test3 {

	public static void main(String args[]) {



		List<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(3);
		List<Integer> list2=new ArrayList<>();
		list2.add(1);
		list2.add(2);
		list2.add(3);
		list2.add(3);
		list.removeAll(list2);
		System.out.println(list.size());




		//list.stream().filter(integer -> integer!=1).map(integer -> integer * 3).forEach(System.out::println);
		//System.out.println(list.stream().filter(integer -> integer!=1).map(integer -> integer * 3).distinct().count());



	}

}
