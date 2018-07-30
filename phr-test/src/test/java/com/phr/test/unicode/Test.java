package com.phr.test.unicode;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;

public class Test {

	public static void main(String[] args) {
		
		/*Date date = new Date();
        //计算失效时间，2年
        Calendar cal = Calendar.getInstance();
	    cal.setTime(date);//设置起时间
	    cal.add(Calendar.YEAR, 2);//增加2年
	    Date expireDate=cal.getTime();
	    System.out.println(new Date());
	    System.out.println("输出::"+cal.getTime());*/
		
//		String s="💓 ✹#!♔iPhone";
		String s="🧚‍️🧚‍️🧚‍️";
		
//		 System.out.println(EmojiFilter.containsEmoji(s));
//		    System.out.println(EmojiFilter.filterEmoji(s));
//		System.out.println(stringFilter(s));
		System.out.println(replaceEmoji(s));
	}
	  public static String stringFilter(String str){
		    String pattern = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";
		    String reStr = "";
		    Pattern emoji = Pattern.compile(pattern);
		    Matcher emojiMatcher = emoji.matcher(str);
		    str = emojiMatcher.replaceAll(reStr);
		    return str;
	    	}
	  
	  
	  public static String replaceEmoji(String str) {
//		  String content = str.replaceAll("[\\x{10000}-\\x{10FFFF}]", "");
		  String content =str.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "*");
	        return content;
	    }

	  private static boolean isNotEmojiCharacter(char codePoint) 
	  { 
	  return (codePoint == 0x0) || 
	  (codePoint == 0x9) || 
	  (codePoint == 0xA) || 
	  (codePoint == 0xD) || 
	  ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || 
	  ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || 
	  ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)); 
	  } 
	  /** 
	  * 过滤emoji 或者 其他非文字类型的字符 
	  * @param source 
	  * @return 
	  */ 
	  public static String filterEmoji(String source) 
	  { 
	  int len = source.length(); 
	  StringBuilder buf = new StringBuilder(len); 
	  for (int i = 0; i < len; i++) 
	  { 
	  char codePoint = source.charAt(i); 
	  if (isNotEmojiCharacter(codePoint)) 
	  { 
	  buf.append(codePoint); 
	  } else{

	  buf.append("*");

	  }
	  } 
	  return buf.toString(); 
	  }
}
