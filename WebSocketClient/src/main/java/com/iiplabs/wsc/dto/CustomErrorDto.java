package com.iiplabs.wsc.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class CustomErrorDto implements Serializable {

	@JsonProperty("error_code")
	private String code;
	@JsonProperty("error_description")
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
