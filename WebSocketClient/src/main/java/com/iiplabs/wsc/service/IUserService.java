package com.iiplabs.wsc.service;

import com.iiplabs.wsc.dto.AuthenticationRequestDto;
import com.iiplabs.wsc.dto.BaseMessageDto;

public interface IUserService {

	public BaseMessageDto doLogin(AuthenticationRequestDto requestDto);
	
}
