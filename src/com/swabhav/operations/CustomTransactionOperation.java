package com.swabhav.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swabhav.model.Transaction;
import com.swabhav.model.User;

public class CustomTransactionOperation extends TransactionOperation {
	public List<Transaction> getChildrenTransactions(int accountId) throws SQLException {
		List<Transaction> transactions = new ArrayList<Transaction>();
		PreparedStatement statement = connection.prepareStatement("select * from transactions where sender_accountid = ? OR receiver_accountid = ? ORDER BY transaction_id DESC  limit 3 ");
		statement.setInt(1, accountId);
		statement.setInt(2, accountId);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			Transaction transaction = new Transaction();
			transaction.setTransactionId(result.getInt(1));
			transaction.setSender_account(new AccountOperations().get(result.getInt(2)));
			transaction.setReceiver_account(new AccountOperations().get(result.getInt(3)));
			transaction.setTransactionAmount(result.getDouble(4));
			transaction.setDate(result.getDate(5));
			transaction.setTransactionType(new TransactionTypeOperation().get(result.getInt(6)));
			transactions.add(transaction);
		}
		return transactions;
	}
	
	
	public List<Transaction> getChildrenTransactionsPaginatedAndFilter(int accountId, int pageSize, int offSet, String sortBy) throws SQLException {
		List<Transaction> transactions = new ArrayList<Transaction>();
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactions WHERE sender_accountid = ? ORDER BY ? DESC LIMIT ? OFFSET ?");
		statement.setInt(1, accountId);
		statement.setString(2, sortBy);
		statement.setInt(3, pageSize);
		statement.setInt(4, offSet);
		ResultSet result = statement.executeQuery();
		while(result.next()) {
			Transaction transaction = new Transaction();
			transaction.setTransactionId(result.getInt(1));
			transaction.setSender_account(new AccountOperations().get(result.getInt(2)));
			transaction.setReceiver_account(new AccountOperations().get(result.getInt(3)));
			transaction.setTransactionAmount(result.getDouble(4));
			transaction.setDate(result.getDate(5));
			transaction.setTransactionType(new TransactionTypeOperation().get(result.getInt(6)));
			transactions.add(transaction);
		}
		return transactions;
	}
	
	public int getNumberOfPages(int accountId, int pageSize) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("SELECT CEIL(COUNT(*) / ?) AS number_of_pages FROM transactions  WHERE sender_accountid = ?");
		statement.setInt(1, pageSize);
		statement.setInt(2, accountId);
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			return result.getInt(1);
		}
		return 0;
	}
	
	public List<Transaction> getAllTransactionSortedAndFiltered(String sortby, String searchBy, int pageSize, int offSet) throws SQLException {
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		// Base SQL query
		StringBuilder query = new StringBuilder("SELECT * FROM transactions");

		// Append ORDER BY clause if sortby is provided
		if (sortby != null && !sortby.trim().isEmpty()) {
			query.append(" ORDER BY ").append(sortby);
		};
		
		if(offSet >= 0 && pageSize >= 0) {
			query.append(" LIMIT "+ pageSize +" OFFSET " +  offSet);
		};
		
		PreparedStatement statement = connection.prepareStatement(query.toString());

		ResultSet result = statement.executeQuery();
		while(result.next()) {
			Transaction transaction = new Transaction();
			transaction.setTransactionId(result.getInt(1));
			transaction.setSender_account(new AccountOperations().get(result.getInt(2)));
			transaction.setReceiver_account(new AccountOperations().get(result.getInt(3)));
			transaction.setTransactionAmount(result.getDouble(4));
			transaction.setDate(result.getDate(5));
			transaction.setTransactionType(new TransactionTypeOperation().get(result.getInt(6)));
			transactions.add(transaction);
		}
		return transactions;
	}
}
