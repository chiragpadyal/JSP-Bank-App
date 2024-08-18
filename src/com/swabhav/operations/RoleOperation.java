package com.swabhav.operations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sun.media.jfxmedia.logging.Logger;
import com.swabhav.model.AdminRole;
import com.swabhav.model.CustomerRole;
import com.swabhav.model.RoleType;

public class RoleOperation implements Operations<RoleType> {

	@Override
	public RoleType get(int id) throws SQLException {
		RoleType roletype = null;
		PreparedStatement statement;
		statement = connection.prepareStatement("select * from roles_type where role_id = ?");
		statement.setInt(1, id);
		RoleType admin = new AdminRole();
		RoleType customer = new CustomerRole();
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			if(result.getString(2).equalsIgnoreCase(admin.getName())) return admin;
			if(result.getString(2).equalsIgnoreCase(customer.getName())) return customer;
		}
		com.swabhav.Logger.getInstance().log("Selected ROle is " + roletype);
		return roletype;
	}

	@Override
	public void delete(int id) throws SQLException {
		
	}

	@Override
	public RoleType update(int id, RoleType entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(RoleType entity) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RoleType> findSimilar(RoleType entity) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleType> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
