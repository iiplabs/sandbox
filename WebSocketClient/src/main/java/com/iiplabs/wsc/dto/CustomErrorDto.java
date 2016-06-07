package com.iiplabs.wsc.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomErrorDto implements Serializable {

	private String code;
	private String description;
	
	public CustomErrorDto() {
		super();
	}

	@Override
	public String toString() {
		return "CustomErrorDto [code=" + code + ", description=" + description + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
