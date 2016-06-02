package com.iiplabs.cacheservice.cache;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.common.io.Files;
import com.iiplabs.cacheservice.codes.CacheStrategy;
import com.iiplabs.cacheservice.configuration.Constants;

public final class LocalCache {

	private final static ICache<String> MEMORY_CACHE = new MemoryCache<String>(Constants.MEMORY_MAX);
	private final static ICache<String> FILE_CACHE = new FileCache<String>(Files.createTempDir(), Constants.MEMORY_MAX);
	
	private LocalCache() {
		throw new AssertionError();
	}
	
	/**
	 *  
	 * @param key
	 * @return Object from memory or file cache
	 */
	public static synchronized Object get(String key) {
		if (Constants.DEFAULT_STRATEGY.equals(CacheStrategy.FILE)) {
			return FILE_CACHE.get(key);
		}
		return MEMORY_CACHE.get(key);
	}
	
	/**
	 * Method puts Object into memory or file cache
	 * @param key
	 * @param value
	 */
	public static synchronized void put(String key, String value) {
		if (Constants.DEFAULT_STRATEGY.equals(CacheStrategy.FILE)) {
			FILE_CACHE.put(key, value); 
			return;
		}
		MEMORY_CACHE.put(key, value);
	}
	
	/**
	 * Method generates a random key and puts Object with it into memory or file cache
	 * @param value
	 * @return generated key
	 */
	public static synchronized String create(String value) {
		String key = RandomStringUtils.randomAlphanumeric(Constants.NEW_KEY_LEN);
		put(key, value);
		return key;
	}
	
}
