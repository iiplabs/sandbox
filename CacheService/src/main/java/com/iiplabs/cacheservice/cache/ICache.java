package com.iiplabs.cacheservice.cache;

public interface ICache {

	public Object get(String key);
	public void put(String key, Object value);
	public int size();
	
}
