package com.capgemini.bankapplication.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.bankapplication.dao.BankAccountDao;
import com.capgemini.bankapplication.database.Database;
import com.capgemini.bankapplication.dbutil.DbUtil;
import com.capgemini.bankapplication.model.BankAccount;
import com.capgemini.bankapplication.model.Customer;

public class BankAccountDaoImpl implements BankAccountDao {
	
	
	
	@Override
	public double getBalance(long accountId) {
		String query = "select balance from bankAccount where account_Id=? ";
		try(Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);)
		{
			
			statement.setInt(1,(int)accountId);
			ResultSet result=statement.executeQuery() ;
			if(result.next())
			{
				return result.getDouble(1) ;
			}
			
			} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return -1 ;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		
		String query = "UPDATE bankAccount set balance=? where account_Id=? ";
		try(Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);)
		{
			
			statement.setInt(2,(int)accountId);
			statement.setDouble(1, newBalance);
			if(statement.executeUpdate()!=0)
			{
				return true ;
			}
			else
			{
				return false ;
			}
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false ;
		
		
		
		
	}

}
