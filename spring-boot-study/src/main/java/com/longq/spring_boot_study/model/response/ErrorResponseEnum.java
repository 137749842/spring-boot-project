package com.longq.spring_boot_study.model.response;

public enum ErrorResponseEnum {
	
	
	/******************* 用户模块儿错误返回码 1001 - 1050******************/
	/**
	 * 用户名密码错误
	 */
	ERROR_USER_FAILURE("1001","用户名密码错误"),
	/**
	 * 用户输入数字错误
	 */
	ERROR_NUMBER_ILLEGAL("1002","计算结果不存存在,请输入50~190之间的数字"),
	
	
	/******************* 服务器模块儿错误返回码 ******************/
	ERROR_ILLEGAL_PARAMS("9001","参数非法"),
	ERROR_ILLEGA_SIGN("9002","sign错误"),
	ERROR_TOKEN_FAILURE("9003","Token失效"),
	ERROR_SYS("9999","系统异常")
	;
	
	private String code;
	private String message;
	private ErrorResponseEnum(String code,String message) {
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
