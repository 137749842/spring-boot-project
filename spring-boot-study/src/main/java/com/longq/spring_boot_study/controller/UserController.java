package com.longq.spring_boot_study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longq.spring_boot_study.model.request.BaseRequest;
import com.longq.spring_boot_study.model.request.user.LoginDomain;
import com.longq.spring_boot_study.model.response.BaseResponse;
import com.longq.spring_boot_study.model.response.ErrorResponseEnum;
import com.longq.spring_boot_study.model.response.user.LoginResponse;
import com.longq.spring_boot_study.service.ITokenService;

@Controller
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private ITokenService tokenService;
	/**
     * 登录
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public BaseResponse login(@RequestBody LoginDomain domain){
    	System.out.println("用户名:"+domain.getUserName()+",密码:"+domain.getPassword());
    	if("longquan".equals(domain.getUserName()) && "123456".equals(domain.getPassword())){
    		System.out.println("用户名:"+domain.getUserName()+"登录成功");
    		LoginResponse  response = new LoginResponse();
    		//假设用户id=1
    		response.setToken(tokenService.createToken("1", "longquan"));
    		return response;
    	}else{
    		System.out.println("用户名:"+domain.getUserName()+"登录失败，密码错误");
    		return BaseResponse.getErrorResponse(ErrorResponseEnum.ERROR_USER_FAILURE);
    	}
    	
    } 
    
    /**
     * 登出
     * @param domain
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginOut", method = RequestMethod.PUT)
    public BaseResponse loginOut(@RequestBody BaseRequest domain){
    	BaseResponse  response = new LoginResponse();
    	return response;
    } 
}
