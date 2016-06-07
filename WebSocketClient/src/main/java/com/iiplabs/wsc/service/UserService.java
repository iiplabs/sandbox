package com.iiplabs.wsc.service;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iiplabs.wsc.code.TokenStatus;
import com.iiplabs.wsc.dto.AuthenticationRequestDto;
import com.iiplabs.wsc.dto.AuthenticationResponseDto;
import com.iiplabs.wsc.dto.BaseMessageDto;
import com.iiplabs.wsc.dto.LoginForm;
import com.iiplabs.wsc.model.dao.TokenDao;
import com.iiplabs.wsc.model.dao.UserDao;
import com.iiplabs.wsc.repository.ITokenRepository;
import com.iiplabs.wsc.repository.IUserRepository;
import com.iiplabs.wsc.util.DaoFactory;
import com.iiplabs.wsc.util.DtoFactory;
import com.iiplabs.wsc.util.SearchCriteriaUtility;

@Service
@Transactional
public class UserService implements IUserService {

	private static Logger logger = Logger.getLogger(UserService.class);
	
	@Autowired
	private ITokenRepository tokenRepository;

	@Autowired
	private IUserRepository userRepository;

	/**
	 * @param ltpaToken
	 */
	@Transactional
	public BaseMessageDto doLogin(AuthenticationRequestDto requestDto) {
		LoginForm loginForm = requestDto.getData();
		UserDao userDao = userRepository
				.findOne(SearchCriteriaUtility.findUser(loginForm.getEmail(), loginForm.getPassword()));
		if (null == userDao) {
			return DtoFactory.newAuthenticationErrorDto("customer.notFound", "Customer not found");
		}
		
		// invalidate previous tokens
		Collection<TokenDao> userTokens = tokenRepository.findAll(SearchCriteriaUtility.findUserTokens(loginForm.getEmail()));
		if (null != userTokens) {
			for (TokenDao tokenDao : userTokens) {
				tokenDao.setStatus(TokenStatus.EXPIRED.code());
			}
		    tokenRepository.save(userTokens);
		}
		
		// create new token with default expiration in 1 hour
		Date expiryDate = UserService.getExpriryDate();
		AuthenticationResponseDto responseDto = DtoFactory.newAuthenticationResponseDto(expiryDate);
		
		// save token to db
		TokenDao tokenDao = DaoFactory.newTokenDao(responseDto.getData());
		tokenDao.setUser(userDao);
		tokenRepository.save(tokenDao);
		
		return responseDto;
	}

	private static Date getExpriryDate() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR, 1);
		return c.getTime();
	}
}
