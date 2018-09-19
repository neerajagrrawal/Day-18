package com.capgemini.bankapplication.exceptions;
public class AccountNotFoundException extends Exception {

	public AccountNotFoundException(String message)
	{
		super(message);
	}
}