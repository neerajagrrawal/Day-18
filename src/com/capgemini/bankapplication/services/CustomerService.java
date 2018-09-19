package com.capgemini.bankapplication.services;

import com.capgemini.bankapplication.exceptions.AccountNotFoundException;
import com.capgemini.bankapplication.model.Customer;

public interface CustomerService {
	public Customer authenticate(Customer customer) throws AccountNotFoundException;
	public Customer updateProfile(Customer customer);
	public boolean updatePassword(Customer customer,String newPassword,String oldPassword);

}
