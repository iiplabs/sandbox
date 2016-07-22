package com.iiplabs.as2.shared;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.iiplabs.as2.configuration.GlobalConstants;

@Component(GlobalConstants.SHARED_OBJECT_ID)
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionObject implements ISessionObject {

	public SessionObject() {
		super();
	}

}
