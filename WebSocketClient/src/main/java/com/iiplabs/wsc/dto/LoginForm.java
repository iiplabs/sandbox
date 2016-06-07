package com.iiplabs.wsc.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public final class LoginForm implements Serializable {

	private String userName;
	private String password;
	
	public LoginForm() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("LoginForm [userName=%s, password=%s]", userName,
				password);
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
