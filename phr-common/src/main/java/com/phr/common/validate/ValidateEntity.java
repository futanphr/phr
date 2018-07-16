package com.phr.common.validate;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.alibaba.fastjson.JSONObject;
import com.phr.common.annotation.ValidataFieldAnnotation;

/**
 * 
 * 
 * 
 * @author yangzhi
 * @time 2015年11月3日上午10:01:41
 * @email zhi19861117@126.com
 * @version 1.0
 * @类介绍
 */
public class ValidateEntity {

	/**
	 * 验证字段
	 * 
	 * @param obj
	 * @return
	 */
	public static ValidateResult validateFiled(Object obj) {
		JSONObject desc = new JSONObject();
		int code = 1;
		Class<?> curClass = obj.getClass();
		Class<?> superClass = curClass.getSuperclass();

		Field[] superFields = superClass.getDeclaredFields();
		int superLen = superFields.length;
		for (int i = 0; i < superLen; i++) {

			Field field = superFields[i];
			desc = validate(field, obj, code, desc);

		}

		Field[] fields = curClass.getDeclaredFields();
		int fieldsLen = fields.length;
		for (int i = 0; i < fieldsLen; i++) {
			Field field = fields[i];
			desc = validate(field, obj, code, desc);
		}
		if (desc.isEmpty())
			code = 1;
		else {
			code = 0;
		}
		return new ValidateResult(code, desc.toString());
	}

	private static JSONObject validate(Field field, Object obj, int code, JSONObject desc) {
		ValidataFieldAnnotation validateFieldAnnotation = field.getAnnotation(ValidataFieldAnnotation.class);
		if (null != validateFieldAnnotation) {

			// 验证该字段是必填项
			if (validateFieldAnnotation.required() == true) {
				try {

//					String methodName = getMethodName(field.getName());
//					System.out.println(methodName);

					Method method = (Method) obj.getClass().getMethod("get" + getMethodName(field.getName()));
					if (method != null) {
						Object value = method.invoke(obj);
						if (null == value || "".equals(value)) {
							code = 0;
							desc.put(field.getName(), "字段不能为空");
							//
						}
					}
					// field.setAccessible(true);
					// System.out.println(field.getName() + ":" +
					// field.get(obj));
					// System.out.println(JSON.toJSON(obj));

					// if (null == field.get(obj) ||
					// StringUtils.isNullString(field.get(obj))) {
					// code = 0;
					// desc.put(field.getName(), "字段不能为空");
					//
					// }
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}

		return desc;
	}

	// 首字母大写
	private static String getMethodName(String fildeName) throws Exception {
		StringBuffer sbBuffer = new StringBuffer();

		String firstLetter = fildeName.substring(0, 1).toUpperCase();
		sbBuffer.append(firstLetter).append(fildeName.substring(1, fildeName.length()));
		return sbBuffer.toString();
	}
	// public static String getMethodName(String str) {
	// if(str == null || str.length() < 2){
	// return str;
	// }
	// String firstLetter = str.substring(0, 1).toUpperCase();
	// return firstLetter + str.substring(1, str.length());
	// }
}
