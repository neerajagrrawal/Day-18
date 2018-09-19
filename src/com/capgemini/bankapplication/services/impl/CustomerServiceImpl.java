package com.capgemini.bankapplication.services.impl;

import com.capgemini.bankapplication.dao.impl.CustomerDaoImpl;
import com.capgemini.bankapplication.exceptions.AccountNotFoundException;
import com.capgemini.bankapplication.model.Customer;
import com.capgemini.bankapplication.services.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	CustomerDaoImpl custImplObj=new CustomerDaoImpl() ;
	
	@Override
	public Customer authenticate(Customer customer) throws AccountNotFoundException {
		return custImplObj.authenticate(customer) ;
		 
	}

	@Override
	public Customer updateProfile(Customer customer) {
		return custImplObj.updateProfile(customer) ;
	}

	@Override
	public boolean updatePassword(Customer customer,String newPassword,String oldPassword) {
		return custImplObj.updatePassword(customer, newPassword,oldPassword);
		}

}
