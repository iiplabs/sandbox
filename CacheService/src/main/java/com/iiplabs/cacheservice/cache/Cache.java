package com.iiplabs.cacheservice.cache;

public abstract class Cache {

	private int max;
	
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
	public abstract Object get(String key);
	public abstract void put(String key, Object value);
	public abstract int size();
	
}
