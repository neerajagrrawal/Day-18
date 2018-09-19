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
import com.capgemini.bankapplication.services.CustomerService;


@WebServlet("/updatePassword.do")
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext context ;
    CustomerService custServices ;
    RequestDispatcher dispatcher = null; 
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	context=config.getServletContext() ;
    }
   
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String oldPassword=request.getParameter("oldPassword") ;
	 String newPassword=request.getParameter("newPassword") ;
	 HttpSession session = request.getSession();
	 Customer customer=(Customer)session.getAttribute("customer") ;
	 Customer c1=new Customer() ;
	 c1.setCustomerId(customer.getCustomerId());
	 c1.setPassword(customer.getPassword());
	 custServices=(CustomerService)context.getAttribute("customerServices") ;
	// System.out.println(custServices.updatePassword(c1, newPassword, oldPassword));
	 if(!custServices.updatePassword(c1, newPassword, oldPassword))
	 {
		 dispatcher = request.getRequestDispatcher("updatePasswordError.jsp");
		 dispatcher.forward(request, response); 
	 }
	 else
	 {
		 customer.setPassword(newPassword);
		 session.setAttribute("customer", customer);
		 dispatcher = request.getRequestDispatcher("updatePasswordSuccess.jsp");
		 dispatcher.forward(request, response); 
	 }
	 
	 
	
	 
	}

}
