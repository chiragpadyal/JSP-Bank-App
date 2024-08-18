package com.swabhav.model;

public class CustomerRole implements RoleType{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Customer";
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	@Override
	public String toString() {
		return "Customer";
	}

}
