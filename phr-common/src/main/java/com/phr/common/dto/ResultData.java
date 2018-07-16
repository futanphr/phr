package com.phr.common.dto;

public class ResultData {

	/**失败状态0*/
	public static final String STATUS_ERROR="0";
	/**成功状态1*/
	public static final String STATUS_SUCC="1";

	/**没有查到数据*/
	public static final String STATUS_NO_DATA="1000";
	/**必填项参数为空*/
	public static final String STATUS_PARAM_EMPTY="1001";
	/**参数异常*/
	public static final String STATUS_PARAM_ERROR="1002";
	/**来源错误*/
	public static final String STATUS_SOURCE_ERROR="1003";
	/**参数值不在处理的范围内*/
	public static final String STATUS_UNRECOGNIZED="1004";
	/**验签失败*/
	public static final String STATUS_SIGN="1005";
	/**重复提交*/
	public static final String STATUS_REPEAT="1006";
	/**非法请求*/
	public static final String STATUS_BAD_REQUEST = "1007";
	/**token无效，请重新登录*/
	public static final String STATUS_TOKEN_INVALID = "1008";
	/**请求的时间戳_t超过30分钟*/
	public static final String STATUS_TIME_INVALID = "1009";
	private String message = "成功";

	private String status = STATUS_SUCC;

	private Object data;
	


	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
