package com.iiplabs.wsc.util;

import java.util.Date;

import com.iiplabs.wsc.dto.AuthenticationErrorDto;
import com.iiplabs.wsc.dto.AuthenticationRequestDto;
import com.iiplabs.wsc.dto.AuthenticationResponseDto;
import com.iiplabs.wsc.dto.CustomErrorDto;
import com.iiplabs.wsc.dto.LoginForm;
import com.iiplabs.wsc.dto.TokenDto;

public final class DtoFactory {

	private DtoFactory() {
		throw new AssertionError();
	}

	public static AuthenticationRequestDto newAuthenticationRequestDto(LoginForm loginForm) {
		AuthenticationRequestDto dto = new AuthenticationRequestDto();
		dto.setData(loginForm);
		return dto;
	}

	public static AuthenticationResponseDto newAuthenticationResponseDto(Date expiryDate) {
		TokenDto tokenDto = new TokenDto();
		tokenDto.setExpiryDate(expiryDate);
		return new AuthenticationResponseDto(tokenDto);
	}

	public static AuthenticationErrorDto newAuthenticationErrorDto(String code, String description) {
		CustomErrorDto customErrorDto = new CustomErrorDto();
		customErrorDto.setCode(code);
		customErrorDto.setDescription(description);
		return new AuthenticationErrorDto(customErrorDto);
	}

}
