package com.capgemini.bankapplication.services.impl;

import com.capgemini.bankapplication.dao.BankAccountDao;
import com.capgemini.bankapplication.dao.impl.BankAccountDaoImpl;
import com.capgemini.bankapplication.database.Database;
import com.capgemini.bankapplication.exceptions.AccountNotFoundException;
import com.capgemini.bankapplication.exceptions.InsufficientAccountBalanceException;
import com.capgemini.bankapplication.exceptions.NegativeAmountException;
import com.capgemini.bankapplication.model.BankAccount;
import com.capgemini.bankapplication.model.Customer;
import com.capgemini.bankapplication.services.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {
	BankAccountDao bankDaoObj=new BankAccountDaoImpl() ;

	@Override
	public double getBalance(long accountId) {
		return bankDaoObj.getBalance(accountId) ;
	}

	@Override
	public double withdraw(long accountId, double amount) {
		double balance=bankDaoObj.getBalance(accountId) ;
		if(balance==-1)
			return -1 ;
		bankDaoObj.updateBalance(accountId, balance-amount)  ;
		return bankDaoObj.getBalance(accountId) ;
	}

	@Override
	public double deposit(long accountId, double amount) {
		double balance=bankDaoObj.getBalance(accountId) ;
		if(balance==-1)
			return -1 ;
		bankDaoObj.updateBalance(accountId, (balance+amount))  ;
		return bankDaoObj.getBalance(accountId) ;
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws InsufficientAccountBalanceException,AccountNotFoundException,NegativeAmountException{
		boolean found=false ;
		boolean balProblem=false ;
		boolean negAmount=false ;
		if(bankDaoObj.getBalance(fromAcc)<amount)
		{
			throw new InsufficientAccountBalanceException("Your account balance is low!!") ;
		}
		
		if(amount<0)
		{
			throw new NegativeAmountException("You have entered negative amount") ;
		}
		
		if(withdraw(fromAcc, amount)!=-1)
		{
			if(deposit(toAcc, amount)!=-1)
			{
				found=true ;
				return true ;
			}
				
		}
		if(found==false)
		throw new AccountNotFoundException("The Account Id is incorrect!!") ;
		return false ;
	} 
		
		
	
	

}
