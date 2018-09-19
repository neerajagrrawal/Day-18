package com.capgemini.bankapplication.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.capgemini.bankapplication.dao.CustomerDao;
import com.capgemini.bankapplication.dbutil.DbUtil;
import com.capgemini.bankapplication.exceptions.AccountNotFoundException;
import com.capgemini.bankapplication.model.Customer;


public class CustomerDaoImpl implements CustomerDao {
	
	
	
	@Override
	public Customer authenticate(Customer customer) throws AccountNotFoundException{
		String query3= "select * from customer join bankAccount on customer.account_Id=bankAccount.account_Id WHERE customer.customer_id = ? AND customer.password = ?" ;
		
		
		try (Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query3);) {
				statement.setInt(1,(int)customer.getCustomerId());
				statement.setString(2,customer.getPassword());
				
				ResultSet result=statement.executeQuery() ;
				
				if(result.next()) {
					
				customer.setCustomerName(result.getString(2)) ;
				customer.setEmail(result.getString(4)) ;
				customer.setAddress(result.getString(5)) ;
				
				LocalDate date=result.getDate(6).toLocalDate() ;
				
				customer.setDateOfBirth(date) ;
				
				customer.getAccount().setAccountId((int)result.getLong(7)) ;
				
				customer.getAccount().setAccountType(result.getString(9)) ;
				customer.getAccount().setBalance(result.getDouble(10)) ;
				
				
				return customer ;
				}
				else
				{
					return null ;
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new AccountNotFoundException("Account Id not found!!") ;
		
		
}

	@Override
	public Customer updateProfile(Customer customer) {
		String query = "UPDATE customer set customer_name=?,email=?,location=?,dob=? where customer_id=? ";
		
		try(Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);)
		{
			
			statement.setString(1,customer.getCustomerName());
			statement.setString(2, customer.getEmail());
			statement.setString(3, customer.getAddress());
			statement.setDate(4, Date.valueOf(customer.getDateOfBirth()));
			statement.setInt(5, (int)customer.getCustomerId());
			if(statement.executeUpdate()!=0)
			{
				return customer ;
			}
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null ;
	}

	@Override
	public boolean updatePassword(Customer customer, String newPassword,String oldPassword) {
		
		String query1="SELECT password from customer where customer_id=?" ;
		String query = "UPDATE customer set password=? where customer_id=? ";
		
		try(Connection connection = DbUtil.getConnection();
				PreparedStatement statement = connection.prepareStatement(query);
				PreparedStatement statement1 = connection.prepareStatement(query1);)
		{
			
			statement1.setInt(1,(int)customer.getCustomerId());
			
			ResultSet result=statement1.executeQuery() ;
			if(result.next() && result.getString(1).equals(oldPassword))
			{
				statement.setString(1,newPassword);
				statement.setInt(2, (int)customer.getCustomerId());
				statement.executeUpdate() ;
				return true ;
			}
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false ;
		
	}	
	

}
