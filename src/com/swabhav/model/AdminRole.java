package com.swabhav.model;

public class AdminRole implements RoleType{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Admin";
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public String toString() {
		return "Admin";
	}
}
