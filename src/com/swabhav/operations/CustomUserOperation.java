package com.swabhav.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.swabhav.Logger;
import com.swabhav.model.Account;
import com.swabhav.model.User;

public class CustomUserOperation extends UserOperations {
	
	public User validateCredentails(User user) throws SQLException {
		if(user.getEmail().isEmpty() || user.getPasswordHash().isEmpty()) return null;
		Logger.getInstance().log(user.getEmail()  + " " + user.getPasswordHash());
		PreparedStatement statement = connection.prepareStatement("select * from users where username = ? and password = UNHEX(SHA1(?)) limit 1");
		statement.setString(1, user.getEmail());
		statement.setString(2, user.getPasswordHash());
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			user.setUserId(result.getInt("userid"));
			user.setRole(
					roleOperation.get(result.getInt("role_id"))
					);
			return user;
		}
		return null;
	}
	
	public List<User> getAllUserSortedAndFiltered(String sortBy, String searchByUsername) throws SQLException {
	    // Initialize an empty list to store User objects
	    List<User> users = new ArrayList<>();

	    // Base SQL query
	    StringBuilder queryBuilder = new StringBuilder("SELECT * FROM users");

	    // Add filter condition if searchByUsername is provided
	    if (searchByUsername != null && !searchByUsername.isEmpty()) {
	        queryBuilder.append(" WHERE username LIKE ?");
	    }

	    // Add sorting condition based on sortBy parameter
	    if (sortBy != null && !sortBy.isEmpty()) {
	        queryBuilder.append(" ORDER BY ");
	        switch (sortBy.toLowerCase()) {
	            case "Username":
	                queryBuilder.append("username");
	                break;
	            case "userid":
	                queryBuilder.append("userid");
	                break;
	            // Add more cases as needed for other sorting options
	            default:
	                // Default sorting (e.g., by user ID)
	                queryBuilder.append("userid");
	                break;
	        }
	    } else {
	        // Default sorting if no sortBy is provided
	        queryBuilder.append(" ORDER BY userid");
	    }

	    // Prepare the statement
	    PreparedStatement statement = connection.prepareStatement(queryBuilder.toString());

	    // Set the filter parameter if searchByUsername is provided
	    if (searchByUsername != null && !searchByUsername.isEmpty()) {
	        statement.setString(1, "%" + searchByUsername + "%");
	    }

	    // Execute the query
	    ResultSet resultSet = statement.executeQuery();

	    // Loop through the result set and create User objects
	    while (resultSet.next()) {
	        User user = new User();
	        user.setUserId(resultSet.getInt(1)); // Adjust column name as needed
	        user.setEmail(resultSet.getString(2)); // Adjust column name as needed
	        user.setPasswordHash(resultSet.getString(3)); // Adjust column name as needed
	        user.setRole(roleOperation.get(resultSet.getInt(4))); // Adjust column name as needed
	        users.add(user);
	    }

	    // Return the sorted and filtered list
	    return users;
	}
}
