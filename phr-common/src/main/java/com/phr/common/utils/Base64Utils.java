package com.phr.common.utils;


public class Base64Utils {
	private static String charset = "utf-8";

	/**
	 * 判断是否为BASE加密
	 */
	public static boolean ifBASE(String str) {

		if (StringUtils.isNull(str)) {
			return false;
		}
		try {

			String tempStr = decode(str);
			String tempStr2 = encode(tempStr);
			if (str.equals(tempStr2)) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 64解密
	 * 
	 * @param str
	 * @return
	 */
	public static String decode(String str) {
		if (StringUtils.isNull(str)) {
			return null;
		}
		try {
			byte[] bytes = org.springframework.util.Base64Utils.decode(str.getBytes(charset));

			return new String(bytes, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 64 加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		if (StringUtils.isNull(str)) {
			return null;
		}
		try {
			byte[] bytes = org.springframework.util.Base64Utils.encode(str.getBytes(charset));

			return new String(bytes, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
