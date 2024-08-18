package com.swabhav.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.swabhav.model.Account;
import com.swabhav.model.Transaction;
import com.swabhav.model.User;

public class AccountOperations implements Operations<Account> {

	Operations<User> userOperation = new UserOperations();
	
	@Override
	public Account get(int id) throws SQLException {
		Account account = null;
		PreparedStatement statement = connection.prepareStatement("select * from account_details where acc_id = ?");
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			account = new Account();
			account.setAccountID(result.getInt(1));
			account.setUser(userOperation.get(result.getInt(2)));
			account.setAccountNumber(result.getString(3));
			account.setBalance(result.getDouble(4));
			account.setAccountName(result.getString(5));
			account.setIsActive(result.getBoolean(6));
		};
		return account;
	}

	@Override
	public void delete(int id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("UPDATE `account_details` SET `isactive` = FALSE WHERE `acc_id` = ?");
		statement.setInt(1, id);
		statement.execute();
	}

	@Override
	public Account update(int id, Account entity) throws SQLException {
		Account oldAccount = get(id);
		Account updatedAccount = entity;
		if(entity.getAccountID() == null) updatedAccount.setAccountID(oldAccount.getAccountID());
		if(entity.getAccountName() == null) updatedAccount.setAccountName(oldAccount.getAccountName());
		if(entity.getAccountNumber() == null) updatedAccount.setAccountNumber(oldAccount.getAccountNumber());
		if(entity.getBalance() == null) updatedAccount.setBalance(oldAccount.getBalance());
		if(entity.getUser() == null) updatedAccount.setUser(oldAccount.getUser());
		
		PreparedStatement statement = connection.prepareStatement("UPDATE `account_details` SET `userid` = ?, `accno` = ?, `balance` = ?, `accname` = ? WHERE `acc_id` = ?");
		statement.setInt(1, updatedAccount.getUser().getUserId());
		statement.setString(2, updatedAccount.getAccountNumber());
		statement.setDouble(3, updatedAccount.getBalance());
		statement.setString(4, updatedAccount.getAccountName());
		statement.setInt(5, updatedAccount.getAccountID());
		statement.execute();
		return updatedAccount;
	}

	@Override
	public void add(Account entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO `bank`.`account_details` (`userid`, `accno` , `balance` , `accname`) VALUES (?,?,?,?)");
		statement.setInt(1, entity.getUser().getUserId());
		statement.setString(2, entity.getAccountNumber());
		statement.setDouble(3, entity.getBalance());
		statement.setString(4, entity.getAccountName());
		statement.execute();
	}

	@Override
	public List<Account> findSimilar(Account entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAll() throws SQLException {
		List<Account> accounts = new ArrayList<Account>();
		Account account = null;
		PreparedStatement statement = connection.prepareStatement("select * from account_details");
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			account = new Account();
			account.setAccountID(result.getInt(1));
			account.setUser(userOperation.get(result.getInt(2)));
			account.setAccountNumber(result.getString(3));
			account.setBalance(result.getDouble(4));
			account.setAccountName(result.getString(5));
			accounts.add(account);
		}
		return accounts;
	}
}
