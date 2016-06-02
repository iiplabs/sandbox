package com.iiplabs.cacheservice.cache;

public interface ICache<V> {

	public V get(String key);
	public void put(String key, V value);
	public int size();
	
}
