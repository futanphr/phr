package com.phr.common.validate;

public class ValidateResult {
	public ValidateResult() {

	}

	public ValidateResult(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * 显示1 验证正确，0错误
	 */
	public int code = 1;
	public String desc;

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
