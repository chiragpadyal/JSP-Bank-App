package com.swabhav.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.swabhav.model.Transaction;

public class TransactionOperation  implements Operations<Transaction> {

	protected TransactionTypeOperation transactionTypeOperation = new TransactionTypeOperation();
	protected AccountOperations acccountOperations = new AccountOperations();
	@Override
	public Transaction get(int id) throws SQLException {
		Transaction transaction = null;
		PreparedStatement statement = connection.prepareStatement("select * from transactions where transaction_id = ?");
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			transaction = new Transaction();
			transaction.setTransactionId(result.getInt(1));
			transaction.setSender_account(acccountOperations.get(result.getInt(2)));
			transaction.setReceiver_account(acccountOperations.get(result.getInt(3)));
			transaction.setTransactionAmount(result.getDouble(4));
			transaction.setDate(result.getDate(5));
			transaction.setTransactionType(transactionTypeOperation.get(result.getInt(6)));
		}
		return transaction;
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement statement = connection.prepareStatement("DELETE FROM `bank`.`transactions` WHERE transaction_id = ?");
		statement.setInt(1, id);
		statement.execute();	
	}

	@Override
	public Transaction update(int id, Transaction entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Transaction entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO `bank`.`transactions` (`sender_accountid`, `receiver_accountid`, `transactionamount`, `date`, `type_id`) VALUES (?, ?, ?, ?, ?)");
		statement.setInt(1, entity.getSender_account().getAccountID());
		
		// only receiver id can be null
		if(entity.getReceiver_account() != null) statement.setInt(2, entity.getReceiver_account().getAccountID());
		else statement.setNull(2, Types.NULL);
		
		statement.setDouble(3, entity.getTransactionAmount());
		statement.setDate(4, entity.getDate());
		statement.setInt(5, entity.getTransactionType().getID());
		statement.execute();
	}

	@Override
	public List<Transaction> findSimilar(Transaction entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
