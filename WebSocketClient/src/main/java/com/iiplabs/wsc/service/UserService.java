package com.iiplabs.wsc.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

	private static Logger logger = Logger.getLogger(UserService.class);
	
	/**
	 * @param ltpaToken
	 */
	@Transactional
	public void doLogin(String userName, String password) {
	}
		
}
