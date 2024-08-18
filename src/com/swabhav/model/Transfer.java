package com.swabhav.model;

public class Transfer implements TransactionType{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Transfer";
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 3;
	}

}
