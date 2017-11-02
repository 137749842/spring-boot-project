package com.longq.spring_boot_study.service;

import io.jsonwebtoken.Claims;

/**
 * @author long
 * token校验service
 */
public interface ITokenService {

	

	/**
	 * 生成token
	 * @param id
	 * @param subject
	 * @return
	 */
	public String createToken(String id, String subject) ;
	/**
	 * token校验
	 * @param jwt
	 * @return
	 */
	public boolean checkToken(String jwt);
	/**
	 * 退出登录时，清除accessToken
	 * @param jwt
	 */
	public void clearToken(String jwt);
	/**
	 * 解密 jwt
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public Claims parseJWT(String jwt) throws Exception;
	
}
