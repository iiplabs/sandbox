package com.iiplabs.wsc.dto;

import com.iiplabs.wsc.code.MessageType;

@SuppressWarnings("serial")
public class AuthenticationRequestDto extends BaseMessageDto {

	private LoginForm data;
	
	public AuthenticationRequestDto() {
		super();
		setMessageType(MessageType.LOGIN_CUSTOMER);
	}

	@Override
	public String toString() {
		return "AuthenticationRequestDto [data=" + data + ", getSequenceId()=" + getSequenceId() + ", getMessageType()="
				+ getMessageType() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public LoginForm getData() {
		return data;
	}

	public void setData(LoginForm data) {
		this.data = data;
	}
	
}
