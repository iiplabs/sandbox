package com.iiplabs.cacheservice.test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.iiplabs.cacheservice.cache.LocalCache;
import com.iiplabs.cacheservice.configuration.Constants;

public class CacheServiceTest {

	private static Logger logger = Logger.getLogger(CacheServiceTest.class);
	
	@Test(expected=RuntimeException.class)
	public void doTest() {
		logger.debug(String.format("Cache Strategy: %s", Constants.DEFAULT_STRATEGY));
		logger.debug(String.format("Memory Cache Limit: %d", Constants.MEMORY_MAX));
		logger.debug(String.format("File Cache Limit: %d", Constants.FILE_MAX));

		LocalCache.put("1", "test-1");
		assertNotNull(LocalCache.get("1"));
		LocalCache.put("2", "test-2");
		assertNotNull(LocalCache.get("2"));
		LocalCache.put("3", "test-3");
		assertNotNull(LocalCache.get("3"));
		assertTrue(LocalCache.get("3").equals("test-3"));

		LocalCache.put("4", "test-4");
		assertNull(LocalCache.get("4"));
	}
	
}
