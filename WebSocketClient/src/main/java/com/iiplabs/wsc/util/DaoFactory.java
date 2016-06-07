package com.iiplabs.wsc.util;

import java.util.Date;

import com.iiplabs.wsc.code.TokenStatus;
import com.iiplabs.wsc.dto.TokenDto;
import com.iiplabs.wsc.model.dao.TokenDao;

public final class DaoFactory {

	private DaoFactory() {
		throw new AssertionError();
	}

	public static TokenDao newTokenDao(TokenDto dto) {
		TokenDao dao = new TokenDao();
		dao.setCreatedTime(new Date());
		dao.setExpiryDate(dto.getExpiryDate());
		dao.setStatus(TokenStatus.ACTIVE.code());
		dao.setTokenId(dto.getTokenId().toString());
		return dao;
	}
	
}
