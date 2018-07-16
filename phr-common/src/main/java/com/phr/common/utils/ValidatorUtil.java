package com.phr.common.utils;


import java.util.regex.Pattern;

import com.phr.common.dto.ResultData;


/**
 * <p>Title:</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: 玖富时代</p>
 * @author: penghuari
 * @version 1.0
 */
public class ValidatorUtil {

	  /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";
 
    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
    
    /**
     * 正则表达式：验证密码
     * ^[0-9+$]
     * ^[a-zA-Z+$]
     * ^[~!@#$￥%\\\\^&*(){}\\[\\]+-_=|;:'<,>.，。?/《》]+$
     * ^[a-zA-Z0-9~!@#$￥%\\\\^&*(){}\\[\\]+-_=|;:'<,>.，。?/《》]{6,16}$
     * ^(?![0-9]+$)(?![a-zA-Z]+$)(?!([^(0-9a-zA-Z)])+$)([^(0-9a-zA-Z)]|[a-zA-Z]|[0-9]){6,}$
     * /(?!^[0-9]+$)(?!^[A-z]+$)(?!^[^A-z0-9]+$)^.{6,16}$/
     */
    public static final String REGEX_PASSWORD_NEW = "^[a-zA-Z0-9~!@#$￥%\\\\^&*(){}\\[\\]+-_=|;:'<,>.，。?/《》]{6,16}$";
 
    /**
     * 正则表达式：验证手机号
     *///^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$//^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$
    public static final String REGEX_MOBILE = "^(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])\\d{8}$";
 
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z-_]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
 
    /**
     * bus正则表达式：验证邮箱
     */
    public static final String BUS_REGEX_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    
    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
 
 
    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
 
    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
    public static final String REGEX_EN = "^[a-zA-Z0-9._]+$";
    
    
 
    public static boolean isEn(String str){
    	return Pattern.matches(REGEX_EN, str);
    }
    /**
     * 校验用户名
     * 
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }
 
    /**
     * 校验密码
     * 
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
    
    /**
     * 校验密码
     * 
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPasswordNew(String password) {
        return Pattern.matches(REGEX_PASSWORD_NEW, password);
    }
 
    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isHanZi(String hanzi) {
    	return Pattern.matches("^\\x00-\\xff", hanzi);
    }
 
    /**
     * 校验邮箱
     * 
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
 
    /**
     * BUS校验邮箱
     * 
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isBusEmail(String email) {
    	return Pattern.compile(BUS_REGEX_EMAIL).matcher(email).matches();
	}
    
    /**
     * 校验汉字
     * 
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }
 
    /**
     * 校验URL
     * 
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }
 
    /**
     * 校验IP地址
     * 
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
    /**
    * @desc: 验证手机号参数必填合法
    *  @param mobile
    *  @return  ResultData
     */
    public static ResultData valiMobileParam(String mobile){
    	if(StringUtils.isNull(mobile)){
			return ResultDataUtil.result(ResultData.STATUS_PARAM_EMPTY,"手机号为空");
		}else{
			if (!isMobile(mobile)) {
				 return ResultDataUtil.result(ResultData.STATUS_PARAM_ERROR, "手机号格式不正确");
			}
		}
    	return null;
    }
}