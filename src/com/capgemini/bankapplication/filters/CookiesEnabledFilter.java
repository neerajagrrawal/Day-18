package com.capgemini.bankapplication.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter("/login.jsp")
public class CookiesEnabledFilter implements Filter {
	RequestDispatcher dispatcher = null;
	public CookiesEnabledFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest=(HttpServletRequest)request ;
		System.out.println(httpRequest.getCookies());
		Cookie[] cookies = httpRequest.getCookies();
		if(cookies == null)
		{
			System.out.println("Hello");
			dispatcher=request.getRequestDispatcher("enableCookies.jsp") ;
			dispatcher.forward(request, response);
		}
		else
		{
			RequestDispatcher requestdispatcher = request.getRequestDispatcher("login.jsp");
			requestdispatcher.forward(request,response);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

}
}
