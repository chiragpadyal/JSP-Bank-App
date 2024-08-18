package com.swabhav.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swabhav.model.Account;

public class CustomAccountOperation extends AccountOperations {

	public List<Account> getAccountsByUserId(int userId) throws SQLException {
		List<Account> accounts = new ArrayList<Account>();
		PreparedStatement statement = connection.prepareStatement("select * from account_details where userid = ? AND isactive = TRUE");
		statement.setInt(1, userId);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			Account account = new Account();
			account.setAccountID(result.getInt(1));
			account.setUser(userOperation.get(result.getInt(2)));
			account.setAccountNumber(result.getString(3));
			account.setBalance(result.getDouble(4));
			account.setAccountName(result.getString(5));
			account.setIsActive(result.getBoolean(6));
			accounts.add(account);
		}
		return accounts;
	}

	public Account getAccountByAccountNumber(String accountNumber) throws SQLException {
		Account account = null;
		PreparedStatement statement = connection.prepareStatement("select * from account_details where accno = ?");
		statement.setString(1, accountNumber);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			account = new Account();
			account.setAccountID(result.getInt(1));
			account.setUser(userOperation.get(result.getInt(2)));
			account.setAccountNumber(result.getString(3));
			account.setBalance(result.getDouble(4));
			account.setAccountName(result.getString(5));
			account.setIsActive(result.getBoolean(6));
		}
		return account;
	}

	public List<Account> getAllAccountSortAndFilter(String sortby, String searchByAccountNameOrNumber)
			throws SQLException {
		List<Account> accounts = new ArrayList<>();

		// Base SQL query
		StringBuilder query = new StringBuilder("SELECT * FROM account_details");

		// Append WHERE clause based on the search criteria
		List<String> conditions = new ArrayList<>();
		if (searchByAccountNameOrNumber != null && !searchByAccountNameOrNumber.trim().isEmpty()) {
			conditions.add("accname LIKE ?");
			conditions.add("accno LIKE ?");
		}

		if (!conditions.isEmpty()) {
			query.append(" WHERE ");
			query.append(String.join(" OR ", conditions));
		}

		// Append ORDER BY clause if sortby is provided
		if (sortby != null && !sortby.trim().isEmpty()) {
			query.append(" ORDER BY ");
	        switch (sortby.toLowerCase()) {
            case "accountId":
            	query.append("accid");
                break;
            case "accountnumber":
            	query.append("accno");
                break;
            case "balance":
            	query.append("balance");
            case "accountname":
            	query.append("accname");
            default:
            	query.append(sortby);
                break;
        }
		}

		// Prepare the SQL statement
		PreparedStatement statement = connection.prepareStatement(query.toString());

		// Set the parameters for search
		int paramIndex = 1;
		if (searchByAccountNameOrNumber != null && !searchByAccountNameOrNumber.trim().isEmpty()) {
			statement.setString(paramIndex++, "%" + searchByAccountNameOrNumber + "%");
			statement.setString(paramIndex++, "%" + searchByAccountNameOrNumber + "%");
		}

		// Execute the query
		ResultSet result = statement.executeQuery();

		// Process the result set
		while (result.next()) {
			Account account = new Account();
			account.setAccountID(result.getInt(1));
			account.setUser(userOperation.get(result.getInt(2)));
			account.setAccountNumber(result.getString(3));
			account.setBalance(result.getDouble(4));
			account.setAccountName(result.getString(5));
			account.setIsActive(result.getBoolean(6));
			accounts.add(account);
		}

		return accounts;
	}
}
