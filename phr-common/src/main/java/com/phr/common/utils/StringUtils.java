package com.phr.common.utils;


import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StringUtils extends org.apache.commons.lang3.StringUtils {
	@SuppressWarnings("unused")
	private static Logger log =  LoggerFactory.getLogger(StringUtils.class);

	/**
	 * 常用正则表达式：匹配非负整数（正整数 + 0）
	 */
	public final static String regExp_integer_1 = "^\\d+$";
	
	/**
	 * 常用正则表达式：匹配正整数
	 */
	public final static String regExp_integer_2 = "^[0-9]*[1-9][0-9]*$";
	
	/**
	 * 常用正则表达式：匹配非正整数（负整数  + 0）
	 */
	public final static String regExp_integer_3 = "^((-\\d+) ?(0+))$";
	
	/**
	 * 常用正则表达式：匹配负整数
	 */
	public final static String regExp_integer_4 = "^-[0-9]*[1-9][0-9]*$";
	
	/**
	 * 常用正则表达式：匹配整数
	 */
	public final static String regExp_integer_5 = "^-?\\d+$";

	/**
	 * 常用正则表达式：匹配非负浮点数（正浮点数 + 0）
	 */
	public final static String regExp_float_1 = "^\\d+(\\.\\d+)?$";
	
	/**
	 * 常用正则表达式：匹配正浮点数
	 */
	public final static String regExp_float_2 = "^(([0-9]+\\.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*\\.[0-9]+) ?([0-9]*[1-9][0-9]*))$"; 
	
	/**
	 * 常用正则表达式：匹配非正浮点数（负浮点数 + 0）
	 */
	public final static String regExp_float_3 = "^((-\\d+(\\.\\d+)?) ?(0+(\\.0+)?))$";
	
	/**
	 * 常用正则表达式：匹配负浮点数
	 */
	public final static String regExp_float_4 = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*\\.[0-9]+) ?([0-9]*[1-9][0-9]*)))$";
	
	/**
	 * 常用正则表达式：匹配浮点数
	 */
	public final static String regExp_float_5 = "^(-?\\d+)(\\.\\d+)?$";

	/**
	 * 常用正则表达式：匹配由26个英文字母组成的字符串
	 */
	public final static String regExp_letter_1 = "^[A-Za-z]+$";
	
	/**
	 * 常用正则表达式：匹配由26个英文字母的大写组成的字符串
	 */
	public final static String regExp_letter_2 = "^[A-Z]+$";
	
	/**
	 * 常用正则表达式：匹配由26个英文字母的小写组成的字符串
	 */
	public final static String regExp_letter_3 = "^[a-z]+$";
	
	/**
	 * 常用正则表达式：匹配由数字和26个英文字母组成的字符串
	 */
	public final static String regExp_letter_4 = "^[A-Za-z0-9]+$";
	
	/**
	 * 常用正则表达式：匹配由数字、26个英文字母或者下划线组成的字符串
	 */
	public final static String regExp_letter_5 = "^\\w+$";

	/**
	 * 常用正则表达式：匹配email地址
	 */
	public final static String regExp_email = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

	/**
	 * 常用正则表达式：匹配url
	 */
	public final static String regExp_url_1 = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";
	
	/**
	 * 常用正则表达式：匹配url
	 */
	public final static String regExp_url_2 = "[a-zA-z]+://[^\\s]*";

	/**
	 * 常用正则表达式：匹配中文字符
	 */
	public final static String regExp_chinese_1 = "[\\u4e00-\\u9fa5]";
	
	/**
	 * 常用正则表达式：匹配双字节字符(包括汉字在内)
	 */
	public final static String regExp_chinese_2 = "[^\\x00-\\xff]"; 

	/**
	 * 常用正则表达式：匹配空行
	 */
	public final static String regExp_line = "\\n[\\s ? ]*\\r";

	/**
	 * 常用正则表达式：匹配HTML标记
	 */
	public final static String regExp_html_1 = "/ <(.*)>.* <\\/\\1> ? <(.*) \\/>/";
	
	/**
	 * 常用正则表达式：匹配首尾空格
	 */
	public final static String regExp_startEndEmpty = "(^\\s*) ?(\\s*$)";

	/**
	 * 常用正则表达式：匹配帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)
	 */
	public final static String regExp_accountNumber = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$"; 

	/**
	 * 常用正则表达式：匹配国内电话号码，匹配形式如 0511-4405222 或 021-87888822
	 */
	public final static String regExp_telephone = "\\d{3}-\\d{8} ?\\d{4}-\\d{7}";

	/**
	 * 常用正则表达式：腾讯QQ号, 腾讯QQ号从10000开始
	 */
	public final static String regExp_qq = "[1-9][0-9]{4,}";

	/**
	 * 常用正则表达式：匹配中国邮政编码
	 */
	public final static String regExp_postbody = "[1-9]\\d{5}(?!\\d)";

	/**
	 * 常用正则表达式：匹配身份证, 中国的身份证为15位或18位
	 */
	public final static String regExp_idCard = "\\d{15} ?\\d{18}";

	/**
	 * 常用正则表达式：IP
	 */
	public final static String regExp_ip = "\\d+\\.\\d+\\.\\d+\\.\\d+";
	
	/**
	 * 字符编码
	 */
	public final static String encoding = "UTF-8";
	public final static Pattern referer_pattern = Pattern.compile("@([^@^\\s^:]{1,})([\\s\\:\\,\\;]{0,1})");//@.+?[\\s:]
	
	private static Random randGen = null;
	private static char[] numbersAndLetters = null;
	
	/**
	 * 验证字符串是否匹配指定正则表达式
	 * @param content
	 * @param regExp
	 * @return
	 */
	public static boolean regExpVali(String content, String regExp){
		Pattern pattern = Pattern.compile(regExp);
		Matcher matcher = pattern.matcher(content);
		return matcher.matches();
	}

	/**
	 * double精度调整
	 * @param doubleValue 需要调整的值123.454
	 * @param format 目标样式".##"
	 * @return
	 */
	public static String decimalFormat(double doubleValue, String format){
		DecimalFormat myFormatter = new DecimalFormat(format);  
		String formatValue = myFormatter.format(doubleValue);
		return formatValue;
	}
	
	/**
	 * Url Base64编码
	 * 
	 * @param data  待编码数据
	 * @return String 编码数据
	 * @throws Exception
	 */
	public static String encode(String data) throws Exception {
		// 执行编码
		byte[] b = Base64.encodeBase64URLSafe(data.getBytes(encoding));
		return new String(b, encoding);
	}

	/**
	 * Url Base64解码
	 * 
	 * @param data
	 *            待解码数据
	 * @return String 解码数据
	 * @throws Exception
	 */
	public static String decode(String data) throws Exception {
		// 执行解码
		byte[] b = Base64.decodeBase64(data.getBytes(encoding));
		return new String(b, encoding);
	}

	/**
	 * URL编码（utf-8）
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncode(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据内容类型判断文件扩展名
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType)){
			fileExt = ".jpg";
			
		} else if ("audio/mpeg".equals(contentType)){
			fileExt = ".mp3";
			
		} else if ("audio/amr".equals(contentType)){
			fileExt = ".amr";
			
		} else if ("video/mp4".equals(contentType)){
			fileExt = ".mp4";
			
		} else if ("video/mpeg4".equals(contentType)){
			fileExt = ".mp4";
		}
		
		return fileExt;
	}

	/**
	 * 获取bean名称
	 * @param bean
	 * @return
	 */
	public static String beanName(Object bean) {
		String fullClassName = bean.getClass().getName();
		String classNameTemp = fullClassName.substring(fullClassName.lastIndexOf(".") + 1, fullClassName.length());
		return classNameTemp.substring(0, 1) + classNameTemp.substring(1);
	}
	
	/**
	 * 首字母转小写
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
        if(Character.isLowerCase(s.charAt(0))){
        	return s;
        } else {
        	return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
	
	/**
	 * 首字母转大写
	 * @param s
	 * @return
	 */
    public static String toUpperCaseFirstOne(String s) {
        if(Character.isUpperCase(s.charAt(0))){
        	return s;
        } else {
        	return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
    
    /**
	 * 生成随机码
	 * 
	 * @return
	 */
	public static final String randomUUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 生成随机码，去掉-，共32位
	 * 
	 * @return
	 */
	public static final String randomUUIDSplit() {
		return randomUUID().replaceAll("-", "");
	}
	 /**
     * 将驼峰风格替换为下划线风格
     */
    public static String camelhumpToUnderline(String str) {
        Matcher matcher = Pattern.compile("[A-Z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() + i, matcher.end() + i, "_" + matcher.group().toLowerCase());
        }
        if (builder.charAt(0) == '_') {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }
    /**
     * 将下划线风格替换为驼峰风格
     * @author penghuari
     * @createDate 2015年12月29日
     * @updateDate 
     * @param str
     * @return
     */
    public static String underlineToCamelhump(String str) {
        Matcher matcher = Pattern.compile("_[A-Z]").matcher(str);
        StringBuilder builder = new StringBuilder(str);
        for (int i = 0; matcher.find(); i++) {
            builder.replace(matcher.start() - i, matcher.end() - i, matcher.group().substring(1).toUpperCase());
        }
        if (Character.isUpperCase(builder.charAt(0))) {
            builder.replace(0, 1, String.valueOf(Character.toLowerCase(builder.charAt(0))));
        }
        return builder.toString();
    }
    /**
     * 指定位数模糊字符串
     * @author penghuari
     * @createDate 2016年2月23日
     * @updateDate 
     * @param str 原始字符串
     * @param startIndex 开始索引
     * @param len 模糊长度
     * @param vague 替换字符
     * @return 返回对应位置替换为对应字符的字符串
     */
    public static String getVague(String str,int startIndex,int len,String vague){
    	if(str.length()< startIndex+len){
    		return str;
    	}
		StringBuffer sb=new StringBuffer();
		sb.append(str.substring(0, startIndex));
		for(int i=0;i<len;i++){
			sb.append(vague);
		}
		sb.append(str.substring(startIndex+len));
		return sb.toString();
	}
    /**
     * 手机号脱敏
     * @author penghuari
     * @createDate 2016年5月17日
     * @updateDate 
     * @param mobile
     * @return
     */
    public static String getMobileVague(String mobile){
    	return getVague(mobile, 3, 4, "*");
    }
    /**
     * 身份证号脱敏
     * @author penghuari
     * @createDate 2016年5月17日
     * @updateDate 
     * @param certId
     * @return
     */
    public static String getCertIdVague(String certId){
    	return getVague(certId, 4, 10, "*");
    }
    
    /**
     * 银行卡脱敏
     * @author penghuari
     * @createDate 2016年5月17日
     * @updateDate 
     * @param certId
     * @return
     */
    public static String getcardNoVague(String cardNo){
    	if(isNotEmpty(cardNo)){
    		return getVague(cardNo, 0, cardNo.length() - 4, "*");
    	}
    	return "";
    }
	/**
	 * 产生随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static final String randomNumber(int length) {
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new Random();
			numbersAndLetters = ("0123456789").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(9)];
		}
		return new String(randBuffer);
	}
	
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}
	
	public static boolean isNull(String str) {
		if(StringUtils.isBlank(str) || str.equals("null")){
			return true;
		}
		return false;
	}
	/**
	 * 获取用户加密的md5密码
	 * @author penghuari
	 * @createDate 2016年5月27日
	 * @updateDate 
	 * @param pwd
	 * @return
	 */
	/*public static String getMd5Pwd(String pwd){
		String str = EncryptUtil.getInstance().MD5(pwd, SignUtil.OPEN_SALT);
		return str.replaceAll("/", "").replaceAll("\\\\", "").replaceAll("\\+", "").replaceAll("=", "");
	}*/
	/**
	 * 移除字符串中的特殊字符 / \ + = 
	 * @author penghuari
	 * @createDate 2016年5月27日
	 * @updateDate 
	 * @param str
	 * @return
	 */
	public static String getReplaceStr(String str){
		if(isNotEmpty(str)){
			return str.replaceAll("/", "").replaceAll("\\\\", "").replaceAll("\\+", "").replaceAll("=", "");
		}
		return str;
	}
	
	/**
	 * @param key
	 *            传入银行代码 如CCB
	 * @return 返回BUS系统银行所对应的编码
	 */
	/*public static String busBankCode(String key) {
		String s = "";
		if (key == null || "".equals(key)) {
			return s;
		}
		Map<String, Object> map = ChangeCardConstants.BusBankCode();
		s = (String) map.get(key);
		if (s == null || "".equals(s)) {
			s = "";
		}
		return s;
	}*/
}
