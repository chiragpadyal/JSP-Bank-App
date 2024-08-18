package com.swabhav.model;

public class Credit implements TransactionType{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Credit";
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String toString() {
		return "Credit";
	}
	
	

}
