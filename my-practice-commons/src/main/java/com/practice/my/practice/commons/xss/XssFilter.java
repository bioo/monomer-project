package com.practice.my.practice.commons.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XSS过滤
 * 
 * @author FOM
 *
 */
public class XssFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		XssHttpServletRequestWrapper_a xssRequest = new XssHttpServletRequestWrapper_a((HttpServletRequest) request);
		chain.doFilter(xssRequest, response);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
	
	public static void main(String[] args) {
		
	}

}
