package com.iiplabs.cacheservice.web;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ICacheWebService {

	@WebMethod
	public Object getCacheElement(@WebParam(name="key") String key);

	@WebMethod
	public void putCacheElement(@WebParam(name="key") String key, @WebParam(name="value") Object value);

	@WebMethod
	public String createCacheElement(@WebParam(name="value") Object value);
	
}
