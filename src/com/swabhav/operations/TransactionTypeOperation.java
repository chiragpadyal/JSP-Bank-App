package com.swabhav.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.swabhav.model.Credit;
import com.swabhav.model.Debit;
import com.swabhav.model.TransactionType;
import com.swabhav.model.Transfer;

public class TransactionTypeOperation  implements Operations<TransactionType> {

	@Override
	public TransactionType get(int id) throws SQLException {
		TransactionType transactionType = null;
		PreparedStatement statement = connection.prepareStatement("select * from transaction_types where type_id = ?");
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		Credit credit = new Credit();
		Debit debit = new Debit();
		Transfer transfer = new Transfer();
		if(result.next()) {
			if(result.getString("type_name").equals(credit.getName())) return new Credit();
			if(result.getString("type_name").equals(debit.getName())) return new Debit();
			if(result.getString("type_name").equals(transfer.getName())) return new Transfer();
		}
		return transactionType;
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TransactionType update(int id, TransactionType entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(TransactionType entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TransactionType> findSimilar(TransactionType entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionType> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
