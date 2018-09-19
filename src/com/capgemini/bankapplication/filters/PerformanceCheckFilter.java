package com.capgemini.bankapplication.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;


@WebFilter(urlPatterns = "*.do", filterName="performanceCheck")
public class PerformanceCheckFilter implements Filter {

	private ServletContext context;
    public PerformanceCheckFilter() {
    }

	public void destroy() {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		
	    long beforeTime = System.currentTimeMillis();	    
		chain.doFilter(request, response);
		long afterTime = System.currentTimeMillis();
		context.log("Time to prorcess the request by " + req.getServletPath() 
		            + " is " + (afterTime - beforeTime) + " ms");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		context = fConfig.getServletContext();
	}
}