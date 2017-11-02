package com.longq.spring_boot_study.model.request;

/**
 * @author long
 * request基类
 */
public class BaseRequest {

	//登录用户Id
	private long userId;
	//登录用户token
	private String token;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
