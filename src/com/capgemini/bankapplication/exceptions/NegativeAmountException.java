package com.capgemini.bankapplication.exceptions;
public class NegativeAmountException extends Exception {

	public NegativeAmountException(String message)
	{
		super(message);
	}
}