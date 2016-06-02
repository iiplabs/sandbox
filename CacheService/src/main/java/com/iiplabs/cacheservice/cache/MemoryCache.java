package com.iiplabs.cacheservice.cache;

import java.util.HashMap;
import java.util.Map;

public class MemoryCache<V> extends Cache<V> implements ICache<V> {

	private Map<String, V> memoryCache;

	public MemoryCache(int max) {
		super();
		setMax(max);
		memoryCache = new HashMap<String, V>();
	}
	
	@Override
	public V get(String key) {
		return memoryCache.get(key);
	}

	@Override
	public void put(String key, V value) {
		int max = getMax();
		if (size() >= max) {
			throw new RuntimeException(String.format("Max elements (%d) exceeded", max));
		}
		memoryCache.put(key, value);
	}

	public Map<String, V> getMemoryCache() {
		return memoryCache;
	}

	public void setMemoryCache(Map<String, V> memoryCache) {
		this.memoryCache = memoryCache;
	}

	@Override
	public int size() {
		return memoryCache.size();
	}

}
