package com.iiplabs.cacheservice.cache;

import java.util.HashMap;
import java.util.Map;

public class MemoryCache extends Cache implements ICache {

	private Map<String, Object> memoryCache;

	public MemoryCache(int max) {
		super();
		setMax(max);
		memoryCache = new HashMap<String, Object>();
	}
	
	@Override
	public Object get(String key) {
		return memoryCache.get(key);
	}

	@Override
	public void put(String key, Object value) {
		int max = getMax();
		if (size() >= max) {
			throw new RuntimeException(String.format("Max elements (%d) exceeded", max));
		}
		memoryCache.put(key, value);
	}

	public Map<String, Object> getMemoryCache() {
		return memoryCache;
	}

	public void setMemoryCache(Map<String, Object> memoryCache) {
		this.memoryCache = memoryCache;
	}

	@Override
	public int size() {
		return memoryCache.size();
	}

}
