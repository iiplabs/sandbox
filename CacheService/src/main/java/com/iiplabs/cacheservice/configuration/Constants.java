package com.iiplabs.cacheservice.configuration;

import com.iiplabs.cacheservice.codes.CacheStrategy;

public final class Constants {

	private Constants() {
		throw new AssertionError();
	}
	
	public static final int NEW_KEY_LEN = 25;
	
	public static final CacheStrategy DEFAULT_STRATEGY = CacheStrategy.MEMORY;
	
	public static final int MEMORY_MAX = 3;
	public static final int FILE_MAX = 3;
	
}
