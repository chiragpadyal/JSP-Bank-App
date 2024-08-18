package com.swabhav.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.swabhav.model.RoleType;
import com.swabhav.model.User;

public class UserOperations implements Operations<User> {

	protected Operations<RoleType> roleOperation = new RoleOperation();
	
	@Override
	public User get(int id) throws SQLException {
		User user = null;
		PreparedStatement statement = connection.prepareStatement("select * from users where userid = ?");
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		if(result.next()) {
			user = new User();
			user.setUserId(result.getInt(1));
			user.setEmail(result.getString(2));
			user.setPasswordHash(result.getString(3));
		}
		return user;
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User update(int id, User entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(User entity) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO `bank`.`users` (`username`, `password`, `role_id`) VALUES (?, unhex(sha1(?)),?)");
		statement.setString(1, entity.getEmail());
		statement.setString(2, entity.getPasswordHash());
		statement.setInt(3, entity.getRole().getID());
		statement.execute();
	}

	@Override
	public List<User> findSimilar(User entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
