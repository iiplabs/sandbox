package com.iiplabs.cacheservice.cache;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;

public class FileCache extends Cache implements ICache {

	private static Logger logger = Logger.getLogger(FileCache.class);
	
	private File base; // base folder for file cache

	public FileCache(File base, int max) {
		super();
		setBase(base);
		setMax(max);
	}

	/**
	 * Method reads JSON file with Guava and attempts to deserialize into Object with Gson
	 */
	@Override
	public Object get(String key) {
		try {
			String fileName = buildFileName(key);
			String content = Files.toString(new File(fileName), Charsets.UTF_8);
			return new Gson().fromJson(content, Object.class);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method serializes Object with Gson into temporary JSON file and then writes it to a file with Guava
	 */
	@Override
	public void put(String key, Object value) {
		int max = getMax();
		if (size() >= max) {
			throw new RuntimeException(String.format("Max elements (%d) exceeded", max));
		}
		try {
			File tempFile = new File(buildFileName(key));
			logger.debug(String.format("New file cache element: %s", tempFile.getPath()));
			tempFile.deleteOnExit();
			Files.write(new Gson().toJson(value), tempFile, Charsets.UTF_8);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public File getBase() {
		return base;
	}

	public void setBase(File base) {
		this.base = base;
	}

	/**
	 * Method returns number of files in the base folder as the cache size
	 */
	@Override
	public int size() {
		return getBase().listFiles().length;
	}

	private String buildFileName(String key) {
		return String.format("%s/%s", getBase().getPath(), key);
	}
	
}
