package com.longq.spring_boot_study;


import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class HttpTools {
	
	static class Config {
		
	    public static final String DEFAULT_USER_AGENT="Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.85 Safari/537.36";
	    public static final int MAX_RECEIVE_SIZE = 1000 * 1000;
	    public static final long REQUEST_MAX_INTERVAL=1000*60*2;
	    public static final int RETRY=3;
	    public static final long WAIT_THREAD_END_TIME=3000*30;
	    
	    /*最大连续重定向次数*/
	    public static final int MAX_REDIRECT=2;
	    
	    public static final int TIMEOUT_CONNECT = 30000;
	    public static final int TIMEOUT_READ = 30000;
	    public static final int MAX_RETRY=20;
	    
	    /**
	     * 创建默认config
	     * @return
	     */
	    public static RequestConfig createDefaultConfig(){
	    	RequestConfig defaultRequestConfig = RequestConfig.custom()
					  .setSocketTimeout(TIMEOUT_CONNECT)
					  .setConnectTimeout(TIMEOUT_CONNECT)
					  .setConnectionRequestTimeout(TIMEOUT_CONNECT)
					  .build();
	    	return defaultRequestConfig;
	    }
	}

	/**
	 * Post请求
	 * @param url
	 * @param formparams
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String excutePost(String url,Map<String, String> params,Map<String, String> headers)throws Exception{
		CloseableHttpClient httpClient =  HttpClients.custom()
			    .setDefaultRequestConfig(Config.createDefaultConfig())
			    .build();
		try{
//			List<NameValuePair> formparams = new ArrayList<>();
//			for(Map.Entry<String, String> param:params.entrySet()){
//				formparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
//			}
//			UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(formparams, "UTF-8"); 
			HttpPost post = new HttpPost(url);
			post.addHeader( "Content-Type","application/json" );
			//设置Http头
			if(headers !=null && !headers.isEmpty()){
				for(Map.Entry<String, String> e :headers.entrySet()){
					post.addHeader(e.getKey(),e.getValue());
				}
			}
			StringEntity requestEntity =  new StringEntity(JSON.toJSONString(params),"utf-8");
			post.setEntity(requestEntity);
			//发送Http请求，获取response
			HttpResponse httpResponse = httpClient.execute(post);
			//发送Http请求，获取response
			
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode == 200){
				HttpEntity entity = httpResponse.getEntity();
				if(entity != null){
					return EntityUtils.toString(entity,"UTF-8");
				}
			}else{
				throw new Exception("statusCode = " + statusCode);
			}
		}finally{
			if(httpClient != null){
				httpClient.close();
			}
		}
		return null;
	}
	/**
	 * 执行httpGet请求
	 * @param url
	 * @param headers
	 * @return
	 * @throws Exception
	 */
	public static String excuteGet(String url,Map<String, String> headers)throws Exception{
		CloseableHttpClient httpClient =  HttpClients.custom()
			    .setDefaultRequestConfig(Config.createDefaultConfig())
			    .build();
		try{
			HttpGet get = new HttpGet(url);
			//设置Http头
			if(headers !=null && !headers.isEmpty()){
				for(Map.Entry<String, String> e :headers.entrySet()){
					get.addHeader(e.getKey(),e.getValue());
				}
			}
			HttpResponse httpResponse = httpClient.execute(get);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode == 200){
				HttpEntity entity = httpResponse.getEntity();
				if(entity != null){
					return EntityUtils.toString(entity,"UTF-8");
				}
			}else{
				throw new Exception("statusCode = " + statusCode);
			}
		}finally{
			if(httpClient != null){
				httpClient.close();
			}
		}
		return null;
	}
	/**
	 * 执行put请求
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
    public static String excutePut(String url,Map<String, String> params,Map<String, String> headers)throws Exception{  
    	CloseableHttpClient httpClient =  HttpClients.custom()
			    .setDefaultRequestConfig(Config.createDefaultConfig())
			    .build();
		try{
			HttpPut put = new HttpPut(url);
			put.addHeader( "Content-Type","application/json" );  
			if(headers !=null && !headers.isEmpty()){
				for(Map.Entry<String, String> e :headers.entrySet()){
					put.addHeader(e.getKey(),e.getValue());
				}
			}
//	        putMethod.getParams().setParameter( HttpMeths.HTTP_CONTENT_CHARSET, HttpConstants.ENCODED );  
			//发送Http请求，获取response
			StringEntity requestEntity =  new StringEntity(JSON.toJSONString(params),"utf-8");
			put.setEntity(requestEntity);
			HttpResponse httpResponse = httpClient.execute(put);
			//发送Http请求，获取response
			
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if(statusCode == 200){
				HttpEntity entity = httpResponse.getEntity();
				if(entity != null){
					return EntityUtils.toString(entity,"UTF-8");
				}
			}else{
				throw new Exception("statusCode = " + statusCode);
			}
		}finally{
			if(httpClient != null){
				httpClient.close();
			}
		}
		return null;
    }  
    
    public static void main(String[] args) {
		Map<String,String> params =new HashMap<>();
		params.put("userName","wdee");
		params.put("password", "12312312");
		Map<String, String > header = new HashMap<>();
		header.put("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNTA4ODI0ODAxLCJhY2Nlc3NUb2tlbiI6IjlkMDliNzE5ZWRmZTQzM2MwYmYxYmFmOWEwM2Y1Y2JiIiwic3ViIjoid29kZXRva2VuIn0.xzrBAl0352yjBzz8vcxO_AGAspAD_DfjR6ULPrG1Bos");
		try {
			System.out.println(HttpTools.excuteGet("http://localhost:8081/api/calculate/1231",header));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
