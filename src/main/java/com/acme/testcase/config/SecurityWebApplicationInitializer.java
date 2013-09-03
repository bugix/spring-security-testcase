package com.acme.testcase.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(1)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	public boolean enableHttpSessionEventPublisher() {
		return true;
	}
}
