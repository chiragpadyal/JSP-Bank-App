package com.swabhav.model;

import java.sql.Date;

public class Transaction {
	private Integer transactionId;
	private Account sender_account;
	private Account receiver_account;
	private Double transactionAmount;
	private Date date;
	private TransactionType transactionType;
	public Integer getTransactionId() {
		return transactionId;
	}
	public Account getSender_account() {
		return sender_account;
	}
	public Account getReceiver_account() {
		return receiver_account;
	}
	public Double getTransactionAmount() {
		return transactionAmount;
	}
	public Date getDate() {
		return date;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public void setSender_account(Account sender_account) {
		this.sender_account = sender_account;
	}
	public void setReceiver_account(Account receiver_account) {
		this.receiver_account = receiver_account;
	}
	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	@Override
	public String toString() {
		return sender_account.toString();
	}

	
	
	
}
