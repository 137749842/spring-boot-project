package com.longq.spring_boot_study.controller;

public class CalculateControllerTest {

	private static final String CALCULATE_URL = "/api/calculate/";
	
	public static void main(String[] args) {
		String token ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTA4ODM4OTczLCJhY2Nlc3NUb2tlbiI6IjVkMjM0MzFiOTU0ZjZjOGMwZDUxYzZmMjc3YTBiMzQwIiwic3ViIjoibG9uZ3F1YW4ifQ.L5Sx4vr4xj3ytiObOoeSuYJf_ZUNSyIMIrk2mH9RzLs";
		BaseApiTest test = new BaseApiTest(token);
		for(int i=70;i<120;i++){
			System.out.println("计算返回结果:"+test.executeGet(CALCULATE_URL+i));
		}
		
	}
}
