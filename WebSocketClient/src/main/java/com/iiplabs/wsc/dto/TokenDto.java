package com.iiplabs.wsc.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@SuppressWarnings("serial")
public class TokenDto implements Serializable {

	private UUID tokenId;
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
