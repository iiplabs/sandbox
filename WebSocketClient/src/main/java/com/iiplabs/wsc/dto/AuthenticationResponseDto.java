package com.iiplabs.wsc.dto;

import com.iiplabs.wsc.code.MessageType;

@SuppressWarnings("serial")
public class AuthenticationResponseDto extends BaseMessageDto {

	private TokenDto data;
	
	public AuthenticationResponseDto() {
		super();
		setMessageType(MessageType.CUSTOMER_API_TOKEN);
	}

	public AuthenticationResponseDto(TokenDto data) {
		this();
		setData(data);
	}
	
	@Override
	public String toString() {
		return "AuthenticationResponseDto [data=" + data + ", getSequenceId()=" + getSequenceId()
				+ ", getMessageType()=" + getMessageType() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public TokenDto getData() {
		return data;
	}

	public void setData(TokenDto data) {
		this.data = data;
	}

}
