package com.longq.spring_boot_study.controller;

public class UserControllerTest {
	
	private static final  String LOGIN_URL = "/api/user/login";
	
	public static void main(String[] args) {
		
		BaseApiTest test = new BaseApiTest();
		test.getParams().put("userName", "longquan");
		test.getParams().put("password", "1234567");
		System.out.println(test.executePut(LOGIN_URL));
		test.getParams().put("password", "123456");
		System.out.println(test.executePut(LOGIN_URL));
		
	}

}
