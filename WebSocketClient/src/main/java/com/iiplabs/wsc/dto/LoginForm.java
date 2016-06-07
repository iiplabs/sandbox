package com.iiplabs.wsc.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public final class LoginForm implements Serializable {

	private String email;
	private String password;
	
	public LoginForm() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("LoginForm [email=%s, password=%s]", email,
				password);
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
