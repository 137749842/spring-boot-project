package com.longq.spring_boot_study.model.request.user;

/**
 * @author long
 * 2017-10-23 23:44:52
 * 登录请求bean
 */
public class LoginDomain {

	private String userName;
	
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
