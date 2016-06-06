package com.iiplabs.wsc.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Component("authenticationFilter")
public class AuthenticationFilter implements Filter {

	private static Logger logger = Logger.getLogger(AuthenticationFilter.class);

	private static final List<String> SKIP_AUTH = Lists.newArrayList(
			".*\\.js$", 
			".*\\.css$", 
			".*\\.jpg$",
			".*\\.gif$",
			".*\\.png$",
			".*/login\\.jsp$",
			".*/login$",
			".*/logout$",
			".*\\.map$"
	);
	
	@Autowired
	private IUserService userService;
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		
		if (servletRequest instanceof HttpServletRequest) {
			HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;			
			String requestUrl = httpServletRequest.getRequestURI();
			
			// check all requests
			boolean checkRequestAuthorization = checkPathAuthorization(requestUrl);			
			if (checkRequestAuthorization) {
				if (!userService.isLogged()) {
					httpServletRequest.getSession().setAttribute("REQUESTED_URL", requestUrl);
					// forward to login page
					HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
					httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
						
					// break filter chain, requested JSP/servlet will not be executed
					return;
				}
			}
		}
		
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	/**
	 * 
	 * @param requestUrl
	 * @return
	 */
	private static boolean checkPathAuthorization(String requestUrl) {
		return !Iterables.any(SKIP_AUTH, 
				matchPredicate(requestUrl));
	}

    private static Predicate<String> matchPredicate(final String v) {
		return new Predicate<String>() {
			@Override
			public boolean apply(String input) {
				return v.toLowerCase().matches(input);
			}
		};
	}
	
}
