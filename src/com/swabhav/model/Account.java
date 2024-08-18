package com.swabhav.model;

import java.util.Random;

import com.swabhav.exception.AccountException;

public class Account implements Cloneable {
	private Integer accountID;
	private User user;
	private String accountNumber;
	private Double balance;
	private String accountName;
	private Boolean isActive = true;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getAccountID() {
		return accountID;
	}


	public Double getBalance() {
		return balance;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}


	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public void credit(double amount) {
		if(amount == 0) throw new AccountException("Amount can't be zero");
		if(amount > 0) {
			this.balance += amount;
			return;
		}
		throw new AccountException("Amount can't be negative");
	}
	
	public void debit(double amount) {
		if(amount == 0) throw new AccountException("Amount can't be zero");
		if(amount < 0) {
			throw new AccountException("Amount can't be negative");
		}
		if(this.balance >= amount) {
			this.balance = balance - amount;
			return;
		}
		throw new AccountException("Insuffient Balance");
	}
	
    public Account clone() {
    	try {
			return (Account) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			return this;
		}
    }
    
	@Override
	public String toString() {
		return accountNumber;
	}
	
	public String randomAccountNumber() {
		Long accountNumberCopy = 0l;
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			accountNumberCopy += rand.nextInt(10);
			if(i != 9) accountNumberCopy *= 10;
		}
		
		return accountNumberCopy.toString();
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
