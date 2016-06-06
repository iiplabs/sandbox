package com.iiplabs.wsc.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiplabs.wsc.repository.ITokenRepository;
import com.iiplabs.wsc.repository.IUserRepository;

@Service
@Transactional
public class DataService implements IDataService {

	@Autowired
	private ITokenRepository tokenRepository;

	@Autowired
	private IUserRepository userRepository;

	public DataService() {}
	
}
