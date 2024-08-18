package com.swabhav.model;

public class Debit implements TransactionType{

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Debit";
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 2;
	}
	@Override
	public String toString() {
		return "Debit";
	}
}
