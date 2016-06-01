package com.iiplabs.cacheservice.cache;

import java.io.File;
import java.io.IOException;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;

public class FileCache extends Cache implements ICache {

	private String base; // base folder for file cache

	public FileCache(String base, int max) {
		super();
		setBase(base);
		setMax(max);
	}

	/**
	 * Method reads JSON file with Guava and attempts to deserialize into Object with Gson
	 */
	@Override
	public Object get(String key) {
		String fileName = buildFileName(key);
		try {
			String content = Files.toString(new File(fileName), Charsets.UTF_8);
			return new Gson().fromJson(content, Object.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method serializes Object with Gson into JSON file and then writes it to a file with Guava
	 */
	@Override
	public void put(String key, Object value) {
		int max = getMax();
		if (size() >= max) {
			throw new RuntimeException(String.format("Max elements (%d) exceeded", max));
		}
		String fileName = buildFileName(key);
		try {
			Files.write(new Gson().toJson(value), new File(fileName), Charsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	/**
	 * Method returns number of files in the base folder as the cache size
	 */
	@Override
	public int size() {
		return new File(getBase()).listFiles().length;
	}

	private String buildFileName(String key) {
		String format = "%s%s";
		if (!getBase().endsWith("/")) {
			format = "%s/%s";
		}
		return String.format(format, getBase(), key);		
	}
	
}
