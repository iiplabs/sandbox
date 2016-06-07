package com.iiplabs.wsc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
public class TokenDto implements Serializable {

	@JsonProperty("api_token")
	private UUID tokenId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ssZ")
	@JsonProperty("api_token_expiration_date")
	private Date expiryDate;
	
	public TokenDto() {
		super();
		tokenId = UUID.randomUUID();
	}

	@Override
	public String toString() {
		return "TokenDto [tokenId=" + tokenId + ", expiryDate=" + expiryDate + "]";
	}

	public UUID getTokenId() {
		return tokenId;
	}

	public void setTokenId(UUID tokenId) {
		this.tokenId = tokenId;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
