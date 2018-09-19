package com.capgemini.bankapplication.dao;

public interface BankAccountDao {
	public double getBalance(long accountId) ;
	public boolean updateBalance(long accountId, double newBalance) ;

}
