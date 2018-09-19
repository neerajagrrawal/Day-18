package com.capgemini.bankapplication.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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


@WebServlet("/editProfile.do")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext context ;
    CustomerService custServices ;
    RequestDispatcher dispatcher = null;   
   
    @Override
    public void init(ServletConfig config) throws ServletException {
    	context=config.getServletContext() ;
    }
    
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String customerName=request.getParameter("customerName") ;
		//System.out.println(customerName);
		String email=request.getParameter("email") ;
		String address=request.getParameter("address") ;
		System.out.println(request.getParameter("dateOfBirth"));
		LocalDate dateOfBirth=LocalDate.parse(request.getParameter("dateOfBirth")) ;
	
		custServices=(CustomerService)context.getAttribute("customerServices") ;
		HttpSession session = request.getSession();
		Customer customer=(Customer)session.getAttribute("customer") ;
		Customer cust=new Customer(customer.getCustomerId(), customerName, customer.getPassword(), email, address, dateOfBirth, customer.getAccount()) ;
		customer=custServices.updateProfile(cust) ;
		if(customer!=null)
		{
			session.setAttribute("customer", customer);
			dispatcher = request.getRequestDispatcher("editProfileSuccess.jsp");
			 dispatcher.forward(request, response);
		}
		else
		{
			dispatcher = request.getRequestDispatcher("editProfileError.jsp");
			 dispatcher.forward(request, response);
		}
		//System.out.println(session.getAttribute("customer"));
		
	}

}
