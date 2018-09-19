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


@WebServlet("/transferMoney.do")
public class TransferMoneyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletContext context ;
    CustomerService custServices ;
    BankAccountService bankServices ;
    RequestDispatcher dispatcher = null;   
   
    @Override
    public void init(ServletConfig config) throws ServletException {
    	context=config.getServletContext() ;
    }

	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long toAccount=Long.parseLong(request.getParameter("toAccountId")) ;
		double amount=Double.parseDouble(request.getParameter("amount")) ;
		custServices=(CustomerService)context.getAttribute("customerServices") ;
		bankServices=(BankAccountService)context.getAttribute("bankServices") ;
		HttpSession session = request.getSession();
		Customer customer=(Customer)session.getAttribute("customer") ;
		long fromAccount=customer.getAccount().getAccountId() ;
		try {
			if(bankServices.fundTransfer(fromAccount, toAccount, amount))
			{
				customer.getAccount().setBalance(bankServices.getBalance(customer.getAccount().getAccountId()));
			}
			
		}
		catch(Exception e){
			dispatcher = request.getRequestDispatcher("errorMessages.jsp");
			request.setAttribute("exception", e);
			 dispatcher.forward(request, response);
			 
		}
		
		dispatcher = request.getRequestDispatcher("dashboard.jsp");
		 dispatcher.forward(request, response);
	}

}
