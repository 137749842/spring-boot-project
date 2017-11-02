package com.longq.spring_boot_study.model.response;

import java.io.Serializable;

public class BaseResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//请求返回码（）
	private String returnCode="0";
	//错误码返回信息
	private String returnMsg ="成功";
	
	/**
	 * 获取错误返回码
	 * @param error
	 * @return
	 */
	public static BaseResponse getErrorResponse(ErrorResponseEnum error){
		BaseResponse response = new BaseResponse();
		response.returnCode = error.getCode();
		response.returnMsg = error.getMessage();
		return response;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	
}
