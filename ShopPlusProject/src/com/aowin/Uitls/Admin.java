package com.aowin.Uitls;

public class Admin {
	String user;
	String password;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Admin(String user, String password) {
		this.user = user;
		this.password = password;
	}
	
}
