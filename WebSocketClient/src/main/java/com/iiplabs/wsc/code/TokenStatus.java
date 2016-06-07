package com.iiplabs.wsc.code;

import java.util.HashMap;
import java.util.Map;

public enum TokenStatus {
	
	ACTIVE("1"), EXPIRED("0");
	
	private static final Map<String, TokenStatus> codeMap = new HashMap<String, TokenStatus>();
	static {
		codeMap.put(ACTIVE.code(), ACTIVE);
		codeMap.put(EXPIRED.code(), EXPIRED);
	}
	
	private final String code;
	
	TokenStatus(String code) {
		this.code = code;
	}
	
	public String code() { return code; }
	
	public static TokenStatus findByCode(String code) {
		return null == code ? null : codeMap.get(code);
	}
	
}
