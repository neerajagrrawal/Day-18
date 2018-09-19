package com.capgemini.bankapplication.services;

import com.capgemini.bankapplication.exceptions.AccountNotFoundException;
import com.capgemini.bankapplication.exceptions.InsufficientAccountBalanceException;
import com.capgemini.bankapplication.exceptions.NegativeAmountException;

public interface BankAccountService {
	
	public double getBalance(long accountId);
	public double withdraw(long accountId, double amount);
	public double deposit(long accountId, double amount);
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws InsufficientAccountBalanceException,AccountNotFoundException, NegativeAmountException;


}
