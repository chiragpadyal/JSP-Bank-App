package com.swabhav.model;

public class User {
	private Integer userId;
	private String email;
	private String passwordHash;
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	private RoleType role;
	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}
	public Integer getUserId() {
		return userId;
	}
	public String getEmail() {
		return email;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return email;
	}

	
	
	
	
}
