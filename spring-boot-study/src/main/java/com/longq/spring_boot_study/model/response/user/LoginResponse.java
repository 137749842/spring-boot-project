package com.longq.spring_boot_study.model.response.user;

import com.longq.spring_boot_study.model.response.BaseResponse;

public class LoginResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

	
}
