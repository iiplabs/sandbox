package com.iiplabs.wsc.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiplabs.wsc.shared.ISessionObject;

@Service
public class UserService implements IUserService {

	private static Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	private ISessionObject webSessionObject;
	
	/**
	 * @param ltpaToken
	 */
	@Transactional
	public void doLogin(String userName, String password) {
		if (isLogged()) {
			return;
		}
	}
		
	/**
	 * Logout
	 */
	@Transactional
	public void doLogout() {
	}
	
	/**
	 * Method is not checking SMS user object
	 * @return
	 */
	@Transactional
	public boolean isLogged() {
		return false;
	}
	
}
