package com.iiplabs.wsc.dto;

import com.iiplabs.wsc.code.MessageType;

@SuppressWarnings("serial")
public class AuthenticationErrorDto extends BaseMessageDto {

	private CustomErrorDto data;
	
	public AuthenticationErrorDto() {
		super();
		setMessageType(MessageType.CUSTOMER_ERROR);
	}

	public AuthenticationErrorDto(CustomErrorDto data) {
		this();
		setData(data);
	}
	
	@Override
	public String toString() {
		return "AuthenticationErrorDto [data=" + data + ", getSequenceId()=" + getSequenceId() + ", getMessageType()="
				+ getMessageType() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public CustomErrorDto getData() {
		return data;
	}

	public void setData(CustomErrorDto data) {
		this.data = data;
	}

}
