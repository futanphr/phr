package com.phr.test.unicode;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class Test2 {

	public static void main(String args[]) {
		 /*SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		 Date date = new Date((1537175874 * 1000L));

       System.out.println(formatter.format(date));*/

				//System.out.println("\u8ba2\u5355\u590d\u5ba1\u672a\u901a\u8fc7");
		//System.out.print(new Date().getTime()/1000);
		/*List list=new ArrayList();
		*//*list.add("1");
		list.add("2");*//*

		String s="";
		s=StringUtils.join(list.toArray(), ",");*/

		/*String[] s1={"1","2","3","4"};
		String[] s2={"2","3","6"};
		String[] rs=intersect(s1, s2);
		for(int i=0;i<rs.length;i++){
			System.out.println(rs[i]);
		}*/

		List<String> allCoupon=new ArrayList<>();
		allCoupon.add("1");
		allCoupon.add("2");
		allCoupon.add("3");

		List<String> noInsertCouponList=new ArrayList<>();
		noInsertCouponList.add("1");
		noInsertCouponList.add("2");
		noInsertCouponList.add("3");


		Iterator<String> it = allCoupon.iterator();
		while(it.hasNext()){
			String str = it.next();
			for(String item :noInsertCouponList){
				if(str.equals(item)){
					it.remove();
				}
			}

		}
		allCoupon.stream().forEach(System.out::println);

		String couponCode = StringUtils.join(allCoupon.toArray(), ",");
		System.out.println("couponCode=:"+couponCode);





	}
	/*******************找交集******************/
	public static String[] intersect(String[] s1,String[] s2){
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		LinkedList<String> list = new LinkedList<String>();

		for(String str:s1){
			if(!map.containsKey(str)){
				map.put(str, Boolean.TRUE);//没有找到 就放进去,设为true
			}
		}
		for(String str:s2){
			if(map.containsKey(str)){
				map.put(str, Boolean.FALSE);//找到的话就设置为false
			}
		}
		for(Map.Entry<String,Boolean> e: map.entrySet()){
			if(e.getValue().equals(Boolean.FALSE)){
				list.add(e.getKey());
			}
		}
		String[] res = {};
		return list.toArray(res);
	}
}
