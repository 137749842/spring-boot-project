package com.longq.spring_boot_study.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.longq.spring_boot_study.service.ITokenService;
import com.longq.spring_boot_study.util.MD5Tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenServiceImpl implements ITokenService{

	/***
	 * 公钥
	 */
	private String publicSecretkey = "publicsecretkey";

	/**
	 * 生成token私钥
	 */
	private String privateSecretkey = "privatesecretkey";
	/**
	 * 用与存放用户token
	 * 最好用redis，每次取出来的时候可以设置过期时间，类似sesion
	 * key:用户id
	 * value:accessToken
	 */
	private Map<String, String> tokenMap = new HashMap<String, String>();
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 生成token
	 * @param id
	 * @param subject
	 * @return
	 */
	public String createToken(String id, String subject) {
		long nowMillis = System.currentTimeMillis();
		//生成accessToken,并保存在map中//此处最好保存在redis中，每次取出的时候更新过期时间
		String accessToken = MD5Tools.md5(privateSecretkey + subject+nowMillis);
		tokenMap.put(id, accessToken);
		//生成jwt
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		
		Date now = new Date(nowMillis);
		JwtBuilder builder = Jwts.builder()
				.setId(id)
				.setIssuedAt(now)
				.claim("accessToken",accessToken)
				.setSubject(subject).
				signWith(signatureAlgorithm, publicSecretkey);
//		if (ttlMillis >= 0) {
//			long expMillis = nowMillis + ttlMillis;
//			Date exp = new Date(expMillis);
//			builder.setExpiration(exp);
//		}
		return builder.compact();
	}
	/**
	 * token校验
	 * @param jwt
	 * @return
	 */
	public boolean checkToken(String jwt){
		try {
			Claims cart= this.parseJWT(jwt);
			String id = cart.getId();
			String accessToken = cart.get("accessToken").toString();
			if(tokenMap.containsKey(id) && tokenMap.get(id).equals(accessToken)){
				return true;
			}
		} catch (Exception e) {
			logger.error("token校验出错",e);
		}
		return false;
	}

	/**
	 * 退出登录时，清除accessToken
	 * @param jwt
	 */
	public void clearToken(String jwt){
		try {
			Claims cart= this.parseJWT(jwt);
			String id = cart.getId();
			tokenMap.remove(id);
		} catch (Exception e) {
			logger.error("token清除出错",e);
		}
	}
	/**
	 * 解密 jwt
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public Claims parseJWT(String jwt) throws Exception {
		Claims claims = Jwts.parser().setSigningKey(publicSecretkey).parseClaimsJws(jwt).getBody();
		return claims;
	}
	
	public static void main(String[] args) {
		ITokenService test = new TokenServiceImpl();
		String jwt = test.createToken("1", "wodetoken");
		System.err.println(jwt);
		try {
			Thread.sleep(1000);
			Claims cart = test.parseJWT(jwt);
			System.out.println(cart.getId());
			System.out.println(cart.get("accessToken"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
