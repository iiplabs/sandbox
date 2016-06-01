package com.iiplabs.cacheservice.web;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.iiplabs.cacheservice.cache.LocalCache;

@Component("cacheWebService")
@WebService(endpointInterface = "com.iiplabs.cacheservice.web.ICacheWebService")
public class CacheWebService implements ICacheWebService {

	private static Logger logger = Logger.getLogger(CacheWebService.class);
	
	public CacheWebService() {
		super();
	}

	@WebMethod
	@Override
	public Object getCacheElement(String key) {
		return LocalCache.get(key);
	}

	@WebMethod
	@Override
	public void putCacheElement(String key, Object value) {
		LocalCache.put(key, value);
	}

	@WebMethod
	@Override
	public String createCacheElement(Object value) {
		return LocalCache.create(value);
	}

}
