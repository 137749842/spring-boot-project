package com.longq.spring_boot_study.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.alibaba.fastjson.JSON;
import com.longq.spring_boot_study.model.response.BaseResponse;
import com.longq.spring_boot_study.model.response.ErrorResponseEnum;
import com.longq.spring_boot_study.service.ITokenService;

/**
 * @author long
 * token校验过滤器
 */
@Order(2)
@WebFilter(filterName = "apiAccessFilter", urlPatterns = {
		"/api/calculate/*"
})
public class TokenFilter implements Filter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ITokenService tokenService;
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		final String jwt = request.getHeader("Authorization");
		if(!tokenService.checkToken(jwt)){
			logger.info("token校验失败，token签名串="+jwt);
			servletResponse.setCharacterEncoding("UTF-8"); 
			servletResponse.getWriter()
			.write(JSON.toJSONString(BaseResponse.getErrorResponse(ErrorResponseEnum.ERROR_TOKEN_FAILURE)));
			return;
		}
		filterChain.doFilter(servletRequest, servletResponse);
		
	}

	@Override
	public void destroy() {
		
	}

}
