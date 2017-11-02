package com.longq.spring_boot_study.controller;

import java.util.HashMap;
import java.util.Map;

import com.longq.spring_boot_study.HttpTools;

public class BaseApiTest {

	private String baseUrl = "http://localhost:8081";
	private Map<String,String >header = new HashMap<>();
	private Map<String, String> params= new HashMap<>();
	
	public BaseApiTest() {
	}
	public BaseApiTest(String token) {
		header.put("Authorization", token);
	}
	
	public String executeGet(String url){
		try {
			 return HttpTools.excuteGet(baseUrl+url, header);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String executePut(String url){
		try {
			 return HttpTools.excutePut(baseUrl+url,params, header);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String executePost(String url){
		try {
			 return HttpTools.excutePost(baseUrl+url,params, header);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Map<String, String> getHeader() {
		return header;
	}
	public void setHeader(Map<String, String> header) {
		this.header = header;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	
}
