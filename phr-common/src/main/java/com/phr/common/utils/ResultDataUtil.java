package com.phr.common.utils;

import java.io.Serializable;

import com.phr.common.dto.ResultData;



/**
 * 通用结果返回工具类
 * @author 杜聪聪
 *
 */
public class ResultDataUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 字符串转换为resultdata
	 * 
	 * @param data
	 * @return
	 */
	public static ResultData fromJson(String data) {
		return FastJsonUtils.fromJson(data, ResultData.class);
	}
    /**
     * 错误返回提示消息
     * @param status 状态
     * @param msg 提示信息
     * @param data 返回内容
     * @author: penghuari
     * @version 1.0
     */
	public static ResultData result(String status,String msg,Object data) {
		ResultData resultData = new ResultData();
		resultData.setStatus(status);
		resultData.setMessage(msg);
		resultData.setData(data);
		return resultData;
	}
	/**
	 * 错误返回提示消息
	 * @param status
	 * @param msg
	 * @return
	 */
	public static ResultData result(String status,String msg) {
		return result(status, msg, null);
	}
    /**
     * @param msg 提示信息
     * @param data 返回内容
     * @author: penghuari
     * @version 1.0
     */
    public static ResultData errorResult(String msg,Object data){
    	return result(ResultData.STATUS_ERROR, msg, data);
    }
    /**
     * 错误返回提示消息
     * @param msg 提示信息
     * @author: penghuari
     * @version 1.0
     */
    public static ResultData errorResult(String msg){
    	return result(ResultData.STATUS_ERROR, msg, null);
    }
    /**
     * 直接返回“系统异常，请稍后再试！”
     * @author penghuari
     * @createDate 2016年5月26日
     * @updateDate 
     * @return
     */
    public static ResultData errorResult(){
    	return result(ResultData.STATUS_ERROR, "系统异常，请稍后再试！", null);
    }
    /**
     * 成功返回提示消息
     * @param msg 提示信息
     * @param data 返回内容
     * @author: penghuari
     * @version 1.0
     */
    public static ResultData successResult(String msg,Object data){
    	return result(ResultData.STATUS_SUCC, msg, data);
    }
    /**
     * 成功返回提示消息
     * @param msg 提示信息
     * @author: penghuari
     * @version 1.0
     */
    public static ResultData successResult(String msg){
    	return result(ResultData.STATUS_SUCC, msg, null);
    }
    /**
     * 返回操作成功提示
     * @author penghuari
     * @createDate 2016年5月26日
     * @updateDate 
     * @return
     */
    public static ResultData successResult(){
    	return result(ResultData.STATUS_SUCC, "操作成功！", null);
    }

    /**
     * 未查询到数据
     * @param msg
     * @return
     * @author penghuari
     * @createDate 2017年3月17日
     * @updateDate
     */
    public static ResultData noData(String msg){
    	return result(ResultData.STATUS_NO_DATA, msg);
    }
    /**
     * 参数为空
     * @param msg
     * @return
     * @author penghuari
     * @createDate 2017年3月17日
     * @updateDate
     */
    public static ResultData paramEmpty(String msg){
    	return result(ResultData.STATUS_PARAM_EMPTY, msg);
    }
    /**
     * 参数异常
     * @param msg
     * @return
     * @author penghuari
     * @createDate 2017年3月17日
     * @updateDate
     */
    public static ResultData paramError(String msg){
    	return result(ResultData.STATUS_PARAM_ERROR, msg);
    }
    /**
     * 来源错误
     * @param msg
     * @return
     * @author penghuari
     * @createDate 2017年3月17日
     * @updateDate
     */
    public static ResultData sourceError(String msg){
    	return result(ResultData.STATUS_SOURCE_ERROR, msg);
    }
    /**
     * 验签失败
     * @param msg
     * @return
     * @author penghuari
     * @createDate 2017年3月17日
     * @updateDate
     */
    public static ResultData signFail(String msg){
    	return result(ResultData.STATUS_SIGN, msg);
    }
    /**
     * 重复提交
     * @param msg
     * @return
     * @author penghuari
     * @createDate 2017年3月17日
     * @updateDate
     */
    public static ResultData repeatSubmit(String msg){
    	return result(ResultData.STATUS_REPEAT, msg);
    }
    /**
     * 非法请求
     * @param msg
     * @return
     * @author penghuari
     * @createDate 2017年3月17日
     * @updateDate
     */
    public static ResultData badRequest(String msg){
    	return result(ResultData.STATUS_BAD_REQUEST, msg);
    }
    /**
     * token失效
     * @param msg
     * @return
     * @author penghuari
     * @createDate 2017年3月17日
     * @updateDate
     */
    public static ResultData tokenInvalid(String msg){
    	return result(ResultData.STATUS_TOKEN_INVALID, msg);
    }
    /**
     * 请求的时间戳t超过
     * @param msg
     * @return
     * @author penghuari
     * @createDate 2017年3月17日
     * @updateDate
     */
    public static ResultData timeInvalid(String msg){
    	return result(ResultData.STATUS_TIME_INVALID, msg);
    }
	/**
	 * 是否成功
	 * @param data
	 * @return
	 */
	public static Boolean isSuccess(ResultData data) {
		if (data == null) {
			return Boolean.FALSE;
		}
		if (ResultData.STATUS_SUCC.equals(data.getStatus())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}