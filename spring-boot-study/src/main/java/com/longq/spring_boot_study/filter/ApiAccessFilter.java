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
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import com.alibaba.fastjson.JSON;
import com.longq.spring_boot_study.filter.util.MyWriterWrapper;
import com.longq.spring_boot_study.model.response.BaseResponse;
import com.longq.spring_boot_study.model.response.ErrorResponseEnum;

/**
 * @author long
 * 所有api请求日志过滤器
 */
@Order(1)
@WebFilter(filterName = "apiAccessFilter", urlPatterns = "/api/*")
public class ApiAccessFilter implements Filter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		String apiUrl = ((HttpServletRequest) servletRequest).getRequestURI();
		long current = System.currentTimeMillis();
		MyWriterWrapper responseWrapper = new MyWriterWrapper((HttpServletResponse) servletResponse);
		try {
			filterChain.doFilter(servletRequest, responseWrapper);
		} catch (Exception e) {
			logger.error("api=" + apiUrl + ",执行出错", e);
			servletResponse.setCharacterEncoding("UTF-8"); 
			servletResponse.getWriter()
					.write(JSON.toJSONString(BaseResponse.getErrorResponse(ErrorResponseEnum.ERROR_SYS)));
		}
		String content =null;
		if(responseWrapper.getMyOutputStream() !=null){
			content = responseWrapper.getMyOutputStream().getContent();
		}
		if (content!=null && !"".equals(content) &&responseWrapper.getMyWriter() !=null) {
			content = responseWrapper.getMyWriter().getContent();
		}
		logger.info("api=" + apiUrl + ",执行成功,执行时间=" + (System.currentTimeMillis() - current)+",接口返回内容="+content);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.err.println("大爷的,初始化了");
	}

}
