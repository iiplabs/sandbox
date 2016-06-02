package com.iiplabs.cacheservice.cache;

public abstract class Cache<V> {

	private int max;
	
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	public abstract V get(String key);
	public abstract void put(String key, V value);
	public abstract int size();
	
}
