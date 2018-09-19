package com.capgemini.bankapplication.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.capgemini.bankapplication.model.Customer;
import com.capgemini.bankapplication.services.BankAccountService;
import com.capgemini.bankapplication.services.CustomerService;
import com.capgemini.bankapplication.services.impl.BankAccountServiceImpl;
import com.capgemini.bankapplication.services.impl.CustomerServiceImpl;

@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	CustomerService custServices ;
	BankAccountService bankServices ;
	RequestDispatcher dispatcher = null;
	ServletContext context ;

   
    public LoginController() {
        super();
        }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	custServices=new CustomerServiceImpl() ;
    	bankServices=new BankAccountServiceImpl() ;
    	context=config.getServletContext() ;
    	context.setAttribute("customerServices", custServices);
    	context.setAttribute("bankServices", bankServices);
    	
    }
    
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		long custId=Long.parseLong(request.getParameter("customerId")) ;
		String pass=request.getParameter("pass") ;
		//System.out.println(custId);
		Customer customer=new Customer() ;
		customer.setCustomerId(custId);
		customer.setPassword(pass);
		
		try{
			customer=custServices.authenticate(customer) ;
			System.out.println("Hello");
			if(customer!=null)
			{
				HttpSession session = request.getSession();
				session.setAttribute("customer",customer);
				dispatcher = request.getRequestDispatcher("dashboard.jsp");
				 dispatcher.forward(request, response);
			}
			else
			{
				dispatcher = request.getRequestDispatcher("wrongCredentials.jsp");
				 dispatcher.forward(request, response);
			}
		}
		catch(Exception e)
		{
			dispatcher = request.getRequestDispatcher("errorMessages.jsp");
			request.setAttribute("exception", e);
			 dispatcher.forward(request, response);
		}
		
		
		
			
			
				
}}

